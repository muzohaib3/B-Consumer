package com.example.consumer_sales_blueex.datasource.repo

import com.example.consumer_sales_blueex.models.ShipmentStatusModel
import com.example.consumer_sales_blueex.models.LoginResponseModel
import com.example.consumer_sales_blueex.models.ShipmentGraphModel
import com.example.consumer_sales_blueex.models.ShipmentSummaryModel

interface GeneralRepositoryImpl {

    suspend fun loginUser(rawJson:String): LoginResponseModel
    suspend fun shipmentStatus(rawJson:String): ShipmentStatusModel
    suspend fun shipmentSummary(rawJson:String):ShipmentSummaryModel
    suspend fun shipmentGraphModel(rawJson:String): ShipmentGraphModel

}