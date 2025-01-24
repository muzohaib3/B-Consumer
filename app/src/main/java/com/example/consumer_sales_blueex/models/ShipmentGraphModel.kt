package com.example.consumer_sales_blueex.models

import com.google.gson.annotations.SerializedName

data class ShipmentGraphModel (

    @SerializedName("status"        ) var status        : Int?                     = null,
    @SerializedName("type"          ) var type          : String?                  = null,
    @SerializedName("shipmentTrend" ) var shipmentTrend : ArrayList<ShipmentTrend> = arrayListOf()

)

data class ShipmentTrend (

    @SerializedName("a" ) var a : String? = null,
    @SerializedName("y" ) var y : String? = null

)