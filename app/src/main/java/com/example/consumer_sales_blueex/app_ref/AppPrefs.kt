package com.example.consumer_sales_blueex.app_ref

import android.content.Context
import com.google.gson.Gson


object AppPrefs {

        fun put(context: Context, key: String, value: String) {
            val pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString(key, value).apply()
        }

        fun put(context: Context, key: String, value: Int) {
            val pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putInt(key, value).apply()
        }

        fun put(context: Context, key: String, value: Boolean) {
            val pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putBoolean(key, value).apply()
        }


        fun getBoolean(context: Context, key: String): Boolean {
            val pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            return pref.getBoolean(key, false)
        }

        fun getString(context: Context, key: String): String? {
            val pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            return pref.getString(key, null)
        }

        fun getInt(context: Context, key: String): Int {
            val pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            return pref.getInt(key, 0)
        }

        fun<T> putObject(context: Context,key:String, obj:T){
            val json = Gson().toJson(obj)
            val pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            pref.edit().putString(key,json).apply()
        }

         val PREF = "PREF"
         val nic = "nic"
         val ntn = "ntn"
         val address = "address"
         val cell = "cell"
         val name = "name"
         val email = "email"
         val sDate = "startDate"
         val eDate = "endDate"
         val acNo = "acNo"
         val isLogin = "isLogin"


        object ApiStatus{

            val OK = 200
            val NoContent = 204
            val BadRequest = 400
            val Unauthorized = 401
            val Forbidden = 403
            val NotFound = 404
            val RequestTimeout = 408
            val InternalServerError = 500
        }

    }
