package com.android.myou.UI

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.myou.databinding.ActivityLogInBinding
import com.android.myou.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        val name = binding.name
        val id = binding.id
        val pw = binding.pw
        val rePw = binding.rePw

        if (pw!=rePw){
            binding.warnText.visibility = View.VISIBLE
        }

        binding.getStart.setOnClickListener {

        }
    }
}