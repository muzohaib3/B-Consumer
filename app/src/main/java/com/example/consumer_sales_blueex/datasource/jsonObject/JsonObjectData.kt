package com.example.consumer_sales_blueex.datasource.jsonObject

import org.json.JSONObject

class JsonObjectData {

    fun shipmentJsonObj(
        acno:String, startDate:String,endDate:String,actionType:String
    ):String{
        var json = JSONObject()
        json.put("action","ShipmentGraph")

        var payloadJson = JSONObject()
        payloadJson.put("acno",acno)
        payloadJson.put("startdate",startDate)
        payloadJson.put("enddate",endDate)
        payloadJson.put("actionType",actionType)

        return json.put("payload", payloadJson).toString()
    }

}