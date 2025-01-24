package com.example.consumer_sales_blueex.network

import com.example.consumer_sales_blueex.models.ShipmentStatusModel
import com.example.consumer_sales_blueex.models.LoginResponseModel
import com.example.consumer_sales_blueex.models.ShipmentGraphModel
import com.example.consumer_sales_blueex.models.ShipmentSummaryModel
import com.example.consumer_sales_blueex.network.Routes.Dashboard
import com.example.consumer_sales_blueex.network.Routes.Login
import com.example.consumer_sales_blueex.network.Routes.graph
import com.example.consumer_sales_blueex.network.Routes.summary
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(Login)
    suspend fun loginUser(@Body requestBody: RequestBody):LoginResponseModel

    @POST(Dashboard)
    suspend fun shipmentStatus(@Body requestBody: RequestBody): ShipmentStatusModel

    @POST(summary)
    suspend fun shipmentSummary(@Body requestBody: RequestBody):ShipmentSummaryModel

    @POST(graph)
    suspend fun shipmentGraph(@Body requestBody: RequestBody): ShipmentGraphModel

}