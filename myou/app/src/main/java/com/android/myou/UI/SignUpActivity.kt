package com.android.myou.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.android.myou.Dto.User
import com.android.myou.api.RetrofitClient
import com.android.myou.databinding.ActivityLogInBinding
import com.android.myou.databinding.ActivitySignUpBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val apiService = RetrofitClient.apiservice

        binding.getStart.setOnClickListener {
            val name = binding.name.text.toString()
            val id = binding.id.text.toString()
            val pw = binding.pw.text.toString()
            val rePw = binding.rePw.text.toString()
            Log.e("DATA","{$name $id $pw $rePw}")
            if (pw!=rePw){
                binding.warnText.visibility = View.VISIBLE
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        val response = apiService.signUp(User(name,id,pw))
                        Log.e("API Response", response.toString())
                        goLogIn()
                    } catch (e: Exception) {
                        Log.e("Error", e.message.toString())
                    }
                }
            }

        }
    }

    private fun goLogIn() {
        val intent = Intent(this,LogInActivity::class.java)
        startActivity(intent)
    }
}