package com.example.consumer_sales_blueex.datasource.callback

data class ApiResponseCallback<out T>(val status : Status?, val data : T?, val message:String?) {

    companion object {

        fun <T> success(data: T?): ApiResponseCallback<T> {
            return ApiResponseCallback(Status.SUCCESS,data,null)
        }

        fun <T> error(data: T?): ApiResponseCallback<T> {
            return ApiResponseCallback(Status.ERROR,data,null)
        }

        fun <T> loading(): ApiResponseCallback<T> {
            return ApiResponseCallback(Status.LOADING, data = null, null)
        }

    }
}