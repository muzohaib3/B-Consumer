package com.example.consumer_sales_blueex.models

data class ShipmentStatusModel(
    val status: Int,
    val details: List<Detail>
)

data class Detail(
    val value: Int,
    val colorCode: Int,
    val name: String
)

