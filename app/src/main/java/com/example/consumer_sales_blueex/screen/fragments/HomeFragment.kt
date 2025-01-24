package com.example.consumer_sales_blueex.screen.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.archit.calendardaterangepicker.customviews.CalendarListener
import com.example.consumer_sales_blueex.R
import com.example.consumer_sales_blueex.app_ref.AppPrefs
import com.example.consumer_sales_blueex.app_ref.AppPrefs.acNo
import com.example.consumer_sales_blueex.databinding.FragmentHomeBinding
import com.example.consumer_sales_blueex.datasource.callback.ApiResponseCallback
import com.example.consumer_sales_blueex.datasource.callback.Status
import com.example.consumer_sales_blueex.datasource.jsonObject.JsonObjectData
import com.example.consumer_sales_blueex.datasource.viewmodel.MainViewModel
import com.example.consumer_sales_blueex.models.ShipmentGraphModel
import com.example.consumer_sales_blueex.models.ShipmentStatusModel
import com.example.consumer_sales_blueex.models.ShipmentSummaryModel
import com.example.consumer_sales_blueex.models.ShipmentTrend
import com.example.consumer_sales_blueex.screen.adapter.home.HomeAdapter
import com.example.consumer_sales_blueex.screen.adapter.home.HomeShipmentStatusAdapter
import com.example.consumer_sales_blueex.screen.ktx.convertDate
import com.example.consumer_sales_blueex.screen.ktx.getCurrentDate
import com.example.consumer_sales_blueex.screen.ktx.getDaysAgo
import com.example.consumer_sales_blueex.screen.ktx.isOnline
import com.example.consumer_sales_blueex.screen.ktx.makeGone
import com.example.consumer_sales_blueex.screen.ktx.makeVisible
import com.example.consumer_sales_blueex.screen.ktx.showProgressDialog
import com.example.consumer_sales_blueex.screen.ktx.toFormattedString
import com.example.consumer_sales_blueex.screen.ktx.toJson
import com.example.consumer_sales_blueex.screen.ktx.toast
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.abs

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MainViewModel
    private var cStartDate = ""
    private var cEndDate = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setupObservers()
        fetchInitialData()
    }

    private fun initViews() {
        binding.ivCalendar.setOnClickListener {
            binding.llDatePickerDialog.makeVisible()
            setupDatePicker()
        }
    }

    private fun setupObservers() {
        viewModel.shipmentSummary.observe(viewLifecycleOwner) { handleSummaryResponse(it) }
        viewModel.shipmentStatus.observe(viewLifecycleOwner) { handleStatusResponse(it) }
        viewModel.shipmentGraph.observe(viewLifecycleOwner) { handleGraphResponse(it) }
    }

    private fun fetchInitialData() {
        if (isOnline(requireContext())) {
            val currentDate = getCurrentDate()
            val diffDate = getDaysAgo(7).toFormattedString()
            val acNoPref = AppPrefs.getString(requireContext(), acNo)!!

            val jsonObjectData = JsonObjectData()
            viewModel.shipmentSummary(jsonObjectData.shipmentJsonObj(acNoPref, currentDate, diffDate, "summary"))
            viewModel.shipmentStatus(jsonObjectData.shipmentJsonObj(acNoPref, currentDate, diffDate, "shipmentStatus"))
            viewModel.shipmentGraph(jsonObjectData.shipmentJsonObj(acNoPref, currentDate, diffDate, "graph"))
        } else {
            toast(requireContext(), "No internet connection")
        }
    }

    private fun handleSummaryResponse(response: ApiResponseCallback<ShipmentSummaryModel>) {
        when (response.status) {
            Status.LOADING -> showProgressDialog(requireContext())
            Status.SUCCESS -> {
                response.data?.let {
                    if (it.status == 200) {
                        binding.rvHome.apply {
                            adapter = HomeAdapter(requireContext(), it)
                            layoutManager = GridLayoutManager(requireContext(), 2)
                        }
                    } else {
                        binding.rvHome.adapter = null
                        toast(requireContext(), "No data found")
                    }
                }
            }
            Status.ERROR -> println("Error: ${response.message}")
            else->{}
        }
    }

    private fun handleStatusResponse(response: ApiResponseCallback<ShipmentStatusModel>) {
        when (response.status) {
            Status.LOADING -> showProgressDialog(requireContext())
            Status.SUCCESS -> {
                response.data?.let {
                    if (it.status == 200) {
                        binding.rvShipmentStatus.apply {
                            adapter = HomeShipmentStatusAdapter(requireContext(), it.details)
                            layoutManager = LinearLayoutManager(requireContext())
                        }
                    } else {
                        toast(requireContext(), "No data found")
                    }
                }
            }
            Status.ERROR -> println("Error: ${response.message}")
            else->{

            }
        }
    }

    private fun handleGraphResponse(response: ApiResponseCallback<ShipmentGraphModel>) {
        when (response.status) {
            Status.LOADING -> showProgressDialog(requireContext())
            Status.SUCCESS -> {
                response.data?.let {
                    if (it.status == 200) {
                        setupLineChart(it.shipmentTrend)
                    } else {
                        toast(requireContext(), "No response found")
                    }
                }
            }
            Status.ERROR -> println("Error: ${response.message}")
            else->{

            }
        }
    }

    private fun setupDatePicker() {
        binding.datePicker.setCalendarListener(object : CalendarListener {
            override fun onDateRangeSelected(startDate: Calendar, endDate: Calendar) {
                cStartDate = convertDate(startDate.time.toString())
                cEndDate = convertDate(endDate.time.toString())

                binding.btSelectDate.setOnClickListener {
                    fetchFilteredData(cStartDate, cEndDate)
                    binding.llDatePickerDialog.makeGone()
                }
            }

            override fun onFirstDateSelected(startDate: Calendar) {}
        })
    }

    private fun fetchFilteredData(startDate: String, endDate: String) {
        val acNoPref = AppPrefs.getString(requireContext(), acNo)!!
        val jsonObjectData = JsonObjectData()

        viewModel.shipmentSummary(jsonObjectData.shipmentJsonObj(acNoPref, startDate, endDate, "summary"))
        viewModel.shipmentStatus(jsonObjectData.shipmentJsonObj(acNoPref, startDate, endDate, "shipmentStatus"))
        viewModel.shipmentGraph(jsonObjectData.shipmentJsonObj(acNoPref, startDate, endDate, "graph"))
    }

    private fun setupLineChart(response: List<ShipmentTrend>) {

        if (response.isEmpty()) {
            println("setupLineChart: Response is empty, skipping chart setup.")
            return
        }

        val entries = response.mapNotNull { trend ->
            val xValue = trend.a?.toFloatOrNull()
            val yValue = trend.y?.let { yDate ->
                try {
                    val parsedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(yDate)
                    parsedDate?.time?.toFloat() ?: return@mapNotNull null
                } catch (e: Exception) {
                    null
                }
            }
            if (xValue != null && yValue != null) Entry(xValue, yValue) else null
        }

        if (entries.isEmpty()) {
            println("setupLineChart: No valid entries found.")
            return
        }
    }

}
