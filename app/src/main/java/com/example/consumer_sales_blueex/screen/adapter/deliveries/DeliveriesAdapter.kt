package com.example.consumer_sales_blueex.screen.adapter.deliveries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.consumer_sales_blueex.R
import com.example.consumer_sales_blueex.databinding.DeliveriesItemViewBinding
import com.example.consumer_sales_blueex.databinding.HomeListViewBinding

class DeliveriesAdapter (
    private val context: Context
)
    : RecyclerView.Adapter<DeliveriesAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DeliveriesItemViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.deliveries_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 6

}