package com.example.consumer_sales_blueex.screen.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumer_sales_blueex.R
import com.example.consumer_sales_blueex.databinding.FragmentDeliveryBinding
import com.example.consumer_sales_blueex.databinding.FragmentHomeBinding
import com.example.consumer_sales_blueex.screen.adapter.deliveries.DeliveriesAdapter

class DeliveryFragment : Fragment() {


    private lateinit var binding: FragmentDeliveryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeliveryBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews(){

        binding.rvDeliveries.apply {
            adapter = DeliveriesAdapter(this.context)
            layoutManager = LinearLayoutManager(this.context)
        }

    }

}