package com.example.consumer_sales_blueex

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.consumer_sales_blueex.app_ref.AppPrefs
import com.example.consumer_sales_blueex.app_ref.AppPrefs.isLogin
import com.example.consumer_sales_blueex.screen.HomeActivity
import com.example.consumer_sales_blueex.screen.LoginActivity
import com.example.consumer_sales_blueex.screen.ktx.gotoActivity

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        splashScreen()
    }

    private fun splashScreen(){

        var isLogin = AppPrefs.getBoolean(this, isLogin)

        if (isLogin != null || !isLogin.equals(""))
        {
            when(isLogin){

                true->{
                    Handler().postDelayed({
                        gotoActivity(this,HomeActivity::class.java)
                        finish()
                    },3000)
                }
                else->{
                    Handler().postDelayed({
                        gotoActivity(this,LoginActivity::class.java)
                        finish()
                    },3000)
                }
            }
        }
    }
}