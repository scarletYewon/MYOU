package com.android.myou.UI

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.myou.Dto.Memo
import com.android.myou.Adapter.MyListAdapter
import com.android.myou.App
import com.android.myou.Dto.DiaryListDto
import com.android.myou.api.RetrofitClient
import com.android.myou.databinding.ActivityMyMemoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

class MyMemoActivity : AppCompatActivity() {
    private val binding: ActivityMyMemoBinding by lazy {
        ActivityMyMemoBinding.inflate(layoutInflater)
    }
    private var doubleBackToExitPressedOnce = false
    private val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        // 뒤로가기 버튼을 커스텀 처리
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (doubleBackToExitPressedOnce) {
                    finishAffinity() // 앱 종료
                    return
                }

                doubleBackToExitPressedOnce = true
                Toast.makeText(this@MyMemoActivity, "한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()

                // 2초 후에 doubleBackToExitPressedOnce 변수를 false로 리셋
                handler.postDelayed({
                    doubleBackToExitPressedOnce = false
                }, 2000)
            }
        })

        binding.writeNewMemo.setOnClickListener {
            val intent = Intent(this,WriteMemoActivity::class.java)
            startActivity(intent)
        }
        binding.logo.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val apiService = RetrofitClient.apiservice

        GlobalScope.launch(Dispatchers.IO) {
            try {
                // username 가져오기
                val username: String = App.prefs.getItem("username", "username")
                val response = apiService.getMyAllDiary(username)
                Log.e("API Response", response.toString())

                val itemList = mapResponseToRecycler(response.data)
                Log.e("LLLLIIIISST",itemList.toString())

                withContext(Dispatchers.Main) {
                    // 어댑터 연결
                    recyclerView.adapter = MyListAdapter(this@MyMemoActivity,itemList)
                }

            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }

    }
    private fun mapResponseToRecycler(diaryDtoList: List<DiaryListDto>): List<Memo> {
        return diaryDtoList.map { response ->
            Memo(
                id = response.id,
                title = response.title,
                context = response.summary ,
                username = response.username,
                createdAt = formatDate(response.createdAt)

            )
        }
    }
    fun formatDate(input: String): String {
        // 입력 형식 지정
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS", Locale.getDefault())
        // 출력 형식 지정
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        return try {
            // 입력 문자열을 Date로 변환
            val date = inputFormat.parse(input)
            // 원하는 출력 형식으로 변환
            outputFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            "Invalid Date"
        }
    }
}