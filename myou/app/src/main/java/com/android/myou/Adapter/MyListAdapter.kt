package com.android.myou.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.myou.App
import com.android.myou.R
import com.android.myou.UI.DetailActivity
import com.android.myou.UI.MyMemoActivity
import com.android.myou.api.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyListAdapter (private val context: Context, private val dataList: List<Memo>) :
    RecyclerView.Adapter<MyListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.note_title)
        val content: TextView = itemView.findViewById(R.id.note_content)
        val date: TextView = itemView.findViewById(R.id.note_date)
        val deleteBtn : ImageView = itemView.findViewById(R.id.delete_memo)
        var id : Int = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_my_memo_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = dataList[position]
        holder.id = item.id
        holder.title.text = item.title
        holder.content.text = item.context
        holder.date.text = item.createdAt
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("title", item.title)       // 글 제목 전달
            intent.putExtra("content", item.context)   // 글 내용 전달
            intent.putExtra("date", item.createdAt)    // 작성 날짜 전달
            context.startActivity(intent)
        }
        holder.deleteBtn.setOnClickListener {
            // 삭제 확인 팝업 띄우기
            AlertDialog.Builder(context)
                .setTitle("삭제 확인")
                .setMessage("정말로 삭제하시겠습니까?")
                .setPositiveButton("삭제") { _, _ ->
                    deleteMemo(holder.id)
                }
                .setNegativeButton("취소", null) // 취소 시 아무 작업도 하지 않음
                .show()
        }
    }

    fun deleteMemo(id : Int) {
        val apiService = RetrofitClient.apiservice
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.deleteDiary(id)
                Log.e("API Response", response.toString())
                launch(Dispatchers.Main) {
                    // Intent로 이동
                    val intent = Intent(context, MyMemoActivity::class.java)
                    context.startActivity(intent)
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }

    override fun getItemCount(): Int = dataList.size
}