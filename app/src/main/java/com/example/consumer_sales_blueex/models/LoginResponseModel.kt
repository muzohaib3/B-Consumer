package com.example.consumer_sales_blueex.models

import com.google.gson.annotations.SerializedName

data class LoginResponseModel (

    @SerializedName("status"       )
    val status       : Int?    = null,
    @SerializedName("message"      )
    var message      : String? = null,
    @SerializedName("acno"         )
    var acno         : String? = null,
    @SerializedName("password"     )
    var password     : String? = null,
    @SerializedName("accountTitle" )
    var accountTitle : String? = null,
    @SerializedName("name"         )
    var name         : String? = null,
    @SerializedName("address"      )
    var address      : String? = null,
    @SerializedName("cell"         )
    var cell         : String? = null,
    @SerializedName("email"        )
    var email        : String? = null,
    @SerializedName("ntn"          )
    var ntn          : String? = null,
    @SerializedName("cnic"         )
    var cnic         : String? = null,
    @SerializedName("apiKey"       )
    var apiKey       : String? = null

)