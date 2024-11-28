package com.android.myou.UI

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.myou.R
import com.android.myou.databinding.ActivityDetailBinding
import com.android.myou.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        // 전달된 데이터 받기
        val writer = intent.getStringExtra("writer")?:""
        val title = intent.getStringExtra("title") ?: "제목 없음"
        val content = intent.getStringExtra("content") ?: "내용 없음"
        val date = intent.getStringExtra("date") ?: "날짜 없음"

        // 데이터 표시
        binding.noteTitle.text = title
        binding.noteDate.text = date
        binding.noteWriter.text = writer
        binding.noteContent.text = content
        binding.closeMemo.setOnClickListener {
            onBackPressed()
        }
    }
}