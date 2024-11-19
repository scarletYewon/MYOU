package com.android.myou.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.myou.App
import com.android.myou.Dto.DiaryRequestDto
import com.android.myou.R
import com.android.myou.api.RetrofitClient
import com.android.myou.databinding.ActivityMainBinding
import com.android.myou.databinding.ActivityWriteMemoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WriteMemoActivity : AppCompatActivity() {
    private val binding: ActivityWriteMemoBinding by lazy {
        ActivityWriteMemoBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.cancel.setOnClickListener {
            onBackPressed()
        }
        // api 연결
        val apiService = RetrofitClient.apiservice

        // token 가져오기
        val username: String = App.prefs.getItem("username", "username")

        binding.sendMemoToServer.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    var visibility = false
                    // 스위치 뷰를 ID로 찾기
                    val switchToggle: SwitchCompat = binding.switchToggle

                    // 스위치 상태 변경 리스너 설정
                    switchToggle.setOnCheckedChangeListener { _, isChecked ->
                        if (isChecked) {
                            visibility = false
                        } else {
                            visibility = true
                        }
                    }
                    val title = binding.title.text.toString()
                    val content = binding.content.text.toString()
                    val response = apiService.addDiary(DiaryRequestDto(title,content,username,visibility))
                    Log.e("API response",response.toString())
                    goMyMemo()
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }
            }
        }
    }

    private fun goMyMemo() {
        val intent = Intent(this,MyMemoActivity::class.java)
        startActivity(intent)
    }
}