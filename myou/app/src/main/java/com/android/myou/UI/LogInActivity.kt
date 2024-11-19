package com.android.myou.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.android.myou.App
import com.android.myou.api.RetrofitClient
import com.android.myou.databinding.ActivityLogInBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LogInActivity : AppCompatActivity() {
    private val binding: ActivityLogInBinding by lazy {
        ActivityLogInBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val apiService = RetrofitClient.apiservice

        binding.goLogin.setOnClickListener {
            val id = binding.id.text.toString()
            val pw = binding.pw.text.toString()
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val response = apiService.logIn(id,pw)
                    Log.e("API Response", response.toString())
                    App.prefs.addItem("username", response.data)
                    goMain()
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }
            }
        }

        binding.signUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun goMain() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}
