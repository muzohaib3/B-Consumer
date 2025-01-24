package com.example.consumer_sales_blueex.screen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.consumer_sales_blueex.R
import com.example.consumer_sales_blueex.app_ref.AppPrefs
import com.example.consumer_sales_blueex.app_ref.AppPrefs.acNo
import com.example.consumer_sales_blueex.app_ref.AppPrefs.cell
import com.example.consumer_sales_blueex.app_ref.AppPrefs.email
import com.example.consumer_sales_blueex.app_ref.AppPrefs.isLogin
import com.example.consumer_sales_blueex.app_ref.AppPrefs.name
import com.example.consumer_sales_blueex.app_ref.AppPrefs.nic
import com.example.consumer_sales_blueex.app_ref.AppPrefs.ntn
import com.example.consumer_sales_blueex.databinding.ActivityCreateShipmentBinding
import com.example.consumer_sales_blueex.databinding.ActivityLoginBinding
import com.example.consumer_sales_blueex.datasource.callback.ApiResponseCallback
import com.example.consumer_sales_blueex.datasource.callback.Status
import com.example.consumer_sales_blueex.datasource.viewmodel.MainViewModel
import com.example.consumer_sales_blueex.di.InjectUtils.viewModel
import com.example.consumer_sales_blueex.screen.ktx.gotoActivity
import com.example.consumer_sales_blueex.screen.ktx.obtainViewModel
import com.example.consumer_sales_blueex.screen.ktx.toast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.observeOn
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initializers()

    }

    private fun initializers()
    {
        viewModel = obtainViewModel(MainViewModel::class.java)
    }


    private fun initViews()
    {

        binding.btLogin.setOnClickListener {

            var userName = binding.etUserName.text.toString().trim()
            var password = binding.etUserPassword.text.toString().trim()

            when
            {
                userName.isNullOrEmpty()->{ toast(this,"user name not found !!!") }

                password.isNullOrEmpty()->{ toast(this,"password not found !!!") }

                else-> {
                   loginUser(userName,password)
                    initObserver()
                }
            }
        }
    }

    private fun initObserver(){

        viewModel.loginUser.observe(this){
            when(it.status){

                Status.LOADING->{
                    println("Loaing...")
                }
                Status.SUCCESS->{

                    var response = it.data
                    if (response?.status == 200)
                    {
                        var prefEmail = AppPrefs.put(this,email, response.email!! )
                        var prefCnic = AppPrefs.put(this,nic, response.cnic!!)
                        var prefNtn = AppPrefs.put(this, ntn, response.ntn!!)
                        var prefName = AppPrefs.put(this, name, response.name!!)
                        var prefCell = AppPrefs.put(this, cell, response.cell!! )
                        var acNo = AppPrefs.put(this, acNo, response.acno!! )
                        var isLogin = AppPrefs.put(this, isLogin,true)

                        println("ApiResponseCallback.Success...")
                        println("ApiResponseCallback is $prefEmail email $prefEmail , cnic $prefCnic" +
                                " , ntn $prefNtn, name $prefName")
                        gotoActivity(this,HomeActivity::class.java)
                    }
                    else{

                        toast(this,"${response?.message}")
                    }

                }

                Status.ERROR->{
                    println("ApiResponseCallback.Error...")
                }

                else->{

                }
            }
        }
    }

    private fun loginUser(userId:String, password:String)
    {

        val credentials = JSONObject()
        credentials.put("userID", userId)
        credentials.put("password", password)


        val loginUser = JSONObject()
        loginUser.put("action","login")
        loginUser.put("payload",credentials)
        println("loginUser json : $loginUser")

        viewModel.loginUser(loginUser.toString())
        println("loginUser json : viewModel.loginUser(loginUser.toString())")

    }


}