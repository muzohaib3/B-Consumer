package com.example.consumer_sales_blueex.models

import com.google.gson.annotations.SerializedName

data class ShipmentSummaryModel (

    @SerializedName("status"         ) var status         : Int,
    @SerializedName("booked"         ) var booked         : String,
    @SerializedName("accepted"       ) var accepted       : String,
    @SerializedName("serviceCharges" ) var serviceCharges : String,
    @SerializedName("codAmount"      ) var codAmount      : String

)
