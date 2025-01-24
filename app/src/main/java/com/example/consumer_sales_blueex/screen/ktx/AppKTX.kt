package com.example.consumer_sales_blueex.screen.ktx

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.postDelayed
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.consumer_sales_blueex.app_ref.AppPrefs
import com.example.consumer_sales_blueex.app_ref.AppPrefs.acNo
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


fun gotoActivity(context: Context,activity:Class<*>){
    val intent = Intent(context,activity)
    context.startActivity(intent)
}

fun logMessage(tag:String,message: String){
    Log.i(tag,message)
}

fun View.makeVisible() { this.visibility = View.VISIBLE }
fun View.makeInvisible() { this.visibility = View.INVISIBLE }
fun View.makeGone() { this.visibility = View.GONE }
fun View.enable() { isEnabled = true }
fun View.disable() { isEnabled = false }
fun View.isVisible() : Boolean = this.visibility == View.VISIBLE

fun createJsonRequestBody(jsonString: String): RequestBody {
    val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
    return RequestBody.create(mediaType, jsonString)
}

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProvider.NewInstanceFactory().create(viewModelClass)

fun <T : ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>): T {
    return ViewModelProvider(this)[viewModelClass]
}

fun convertDate(date: String): String {
    val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)

    val parsedDate: Date? = inputFormat.parse(date)
    return if (parsedDate != null) {
        outputFormat.format(parsedDate)
    } else {
        "Invalid Date"
    }
}

fun showProgressDialog(context: Context):Dialog{
    return ProgressDialog.show(context, "", "Loading...", true)
}

fun handlerIntent(unit:Unit, interval:Long){
    Handler().postDelayed({ unit },interval)
}

fun toast(context: Context, message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Date.toFormattedString(): String {
    val sdf = SimpleDateFormat("yyyy/MM/dd")
    return sdf.format(this)
}

// Function to get current date in the desired format
fun getCurrentDate(): String {
    return Date().toFormattedString()
}

fun getDaysAgo(daysAgo: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
    return calendar.time
}

inline fun <reified T : Any> T.toJson(): String = Gson().toJson(this, T::class.java)
inline fun <reified T : Any> String.tObject(): T = Gson().fromJson(this, T::class.java)

fun getStringPref(context:Context,key:String){
    AppPrefs.getString(context, key)
}

fun isOnline(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val n = cm.activeNetwork
        if (n != null) {
            val nc = cm.getNetworkCapabilities(n)
            //It will check for both wifi and cellular network
            return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
        return false
    } else {
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}
