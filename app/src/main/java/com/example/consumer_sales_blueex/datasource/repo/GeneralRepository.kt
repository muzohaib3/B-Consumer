package com.example.consumer_sales_blueex.datasource.repo

import com.example.consumer_sales_blueex.di.InjectUtils.apiService
import com.example.consumer_sales_blueex.models.ShipmentStatusModel
import com.example.consumer_sales_blueex.models.LoginResponseModel
import com.example.consumer_sales_blueex.models.ShipmentGraphModel
import com.example.consumer_sales_blueex.models.ShipmentSummaryModel
import com.example.consumer_sales_blueex.screen.ktx.createJsonRequestBody

class GeneralRepository():GeneralRepositoryImpl {

    override suspend fun loginUser(rawJson: String): LoginResponseModel
    = apiService.loginUser(createJsonRequestBody(rawJson))

    override suspend fun shipmentStatus(rawJson: String): ShipmentStatusModel
    = apiService.shipmentStatus(createJsonRequestBody(rawJson))

    override suspend fun shipmentSummary(rawJson: String): ShipmentSummaryModel
    = apiService.shipmentSummary(createJsonRequestBody(rawJson))

    override suspend fun shipmentGraphModel(rawJson: String): ShipmentGraphModel
    = apiService.shipmentGraph(createJsonRequestBody(rawJson))
}