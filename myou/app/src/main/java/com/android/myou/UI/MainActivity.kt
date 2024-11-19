package com.android.myou.UI

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.myou.Adapter.FeedListAdapter
import com.android.myou.Adapter.Memo
import com.android.myou.R
import com.android.myou.databinding.ActivityMainBinding
import com.android.myou.databinding.ActivitySignUpBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        val recyclerView: RecyclerView = binding.recyclerView

        binding.writeNewMemo.setOnClickListener {
            val intent = Intent(this,WriteMemoActivity::class.java)
            startActivity(intent)
        }
        binding.goMyMemo.setOnClickListener {
            val intent = Intent(this,MyMemoActivity::class.java)
            startActivity(intent)
        }

        // 데이터 준비
        val dataList = listOf(
            Memo("Title 1", "Subtitle 1","dddd","24.11.19")
        )

        // 어댑터 설정
        val adapter = FeedListAdapter(dataList)

        // 레이아웃 매니저 설정
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}