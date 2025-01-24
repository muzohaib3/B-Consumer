package com.example.consumer_sales_blueex.datasource.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consumer_sales_blueex.datasource.callback.ApiResponseCallback
import com.example.consumer_sales_blueex.di.InjectUtils.repository
import com.example.consumer_sales_blueex.models.ShipmentStatusModel
import com.example.consumer_sales_blueex.models.LoginResponseModel
import com.example.consumer_sales_blueex.models.ShipmentGraphModel
import com.example.consumer_sales_blueex.models.ShipmentSummaryModel
import kotlinx.coroutines.launch

class MainViewModel: ViewModel()
{
    /** ViewModel Functions **/

    private val _loginUser = MutableLiveData<ApiResponseCallback<LoginResponseModel>>()
    val loginUser: LiveData<ApiResponseCallback<LoginResponseModel>>
        get() = _loginUser

    private val _shipmentStatus = MutableLiveData<ApiResponseCallback<ShipmentStatusModel>>()
    val shipmentStatus:LiveData<ApiResponseCallback<ShipmentStatusModel>>
        get() = _shipmentStatus

    private val _shipmentSummary = MutableLiveData<ApiResponseCallback<ShipmentSummaryModel>>()
    val shipmentSummary : LiveData<ApiResponseCallback<ShipmentSummaryModel>>
        get() = _shipmentSummary

    private val _shipmentGraph = MutableLiveData<ApiResponseCallback<ShipmentGraphModel>>()
    val shipmentGraph :LiveData<ApiResponseCallback<ShipmentGraphModel>>
        get() = _shipmentGraph


    /** ViewModel Observers **/

    fun loginUser(rawJson: String) {
        viewModelScope.launch{
            try {
                _loginUser.value = ApiResponseCallback.success(repository.loginUser(rawJson))
//                println("Viewmodel Response 200 ------> loginUser ")
            } catch (e: Exception) {
                _loginUser.value = ApiResponseCallback.error( null)
                println("Response found >> loginUser >> ${e.message}")
                println("Response found >> loginUser >> ${e.stackTrace}")
            }
        }
    }

    fun shipmentStatus(rawJson: String) {
        viewModelScope.launch{
            try {
                _shipmentStatus.value = ApiResponseCallback.success(repository.shipmentStatus(rawJson))
//                println("Viewmodel Response 200 ------> shipmentStatus ")
            } catch (e: Exception) {
                _shipmentStatus.postValue(ApiResponseCallback.error( null))
                println("Response Exception >> shipmentStatus >> ${e.message}")
                println("Response Exception >> shipmentStatus >> ${e.stackTrace}")
            }
        }
    }

    fun shipmentSummary(rawJson: String) {
        viewModelScope.launch{
            try {
                _shipmentSummary.value = ApiResponseCallback.success(repository.shipmentSummary(rawJson))
//                println("Viewmodel Response 200 ------> shipmentSummary ")
            } catch (e: Exception) {
                _shipmentSummary.postValue(ApiResponseCallback.error( null))
                println("Response Exception >> _shipmentSummary >> ${e.message}")
                println("Response Exception >> _shipmentSummary >> ${e.stackTrace}")
            }
        }
    }

    fun shipmentGraph(rawJson: String){
        viewModelScope.launch{
            try {
                _shipmentGraph.value = ApiResponseCallback.success(repository.shipmentGraphModel(rawJson))
//                println("Viewmodel Response 200 ------> shipmentGraph ")
            }catch (e:Exception){
                _shipmentGraph.postValue(ApiResponseCallback.error(null))
                println("Response Exception >> _shipmentSummary >> ${e.message}")
                println("Response Exception >> _shipmentSummary >> ${e.stackTrace}")
            }
        }
    }

}