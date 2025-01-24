package com.example.consumer_sales_blueex.screen.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.consumer_sales_blueex.R
import com.example.consumer_sales_blueex.databinding.HomeListViewBinding
import com.example.consumer_sales_blueex.models.ShipmentSummaryModel

class HomeAdapter (
    private val context: Context,
    private val list:ShipmentSummaryModel
)
    : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = HomeListViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_list_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding)
        {
            when(position){
                0->{
                    workType.text = "Shipments Booked"
                    ivHomeItem.setImageResource(R.drawable.ship_booked)
                    tvCount.text = list.booked
                }
                1->{
                    workType.text = "Shipments Accepted"
                    ivHomeItem.setImageResource(R.drawable.sh_accepted)
                    tvCount.text = list.accepted
                }
                2->{
                    workType.text = "Services Charges"
                    ivHomeItem.setImageResource(R.drawable.service_charges)
                    tvCount.text = list.serviceCharges
                }
                3->{
                    workType.text = "Cash Collected"
                    ivHomeItem.setImageResource(R.drawable.cash_collected)
                    tvCount.text = list.codAmount
                }
            }
        }
    }

    override fun getItemCount(): Int = 4

}