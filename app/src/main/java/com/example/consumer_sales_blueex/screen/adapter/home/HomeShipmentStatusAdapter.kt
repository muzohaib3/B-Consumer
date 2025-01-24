package com.example.consumer_sales_blueex.screen.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.consumer_sales_blueex.R
import com.example.consumer_sales_blueex.databinding.ShipmentStatusViewBinding
import com.example.consumer_sales_blueex.models.Detail

class HomeShipmentStatusAdapter (
    private val context: Context,
    private val list: List<Detail>
)
    : RecyclerView.Adapter<HomeShipmentStatusAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ShipmentStatusViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shipment_status_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        with(holder.binding)
        {
            val data = list[position] // Access the current Detail object
            tvHeaderShipment.text = data.name
            lpShipmentType.progress = data.value
            tvShipmentTypeCount.text = data.value.toString()

        }
    }

    override fun getItemCount(): Int = list.size

}