package com.android.myou.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.myou.Dto.Memo
import com.android.myou.R
import com.android.myou.UI.DetailActivity

class FeedListAdapter(private val context: Context, private val dataList: List<Memo>) :
    RecyclerView.Adapter<FeedListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.note_title)
        val content: TextView = itemView.findViewById(R.id.note_content)
        val writer: TextView = itemView.findViewById(R.id.note_writer)
        val date: TextView = itemView.findViewById(R.id.note_date)
        var id : Int = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_memo_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = dataList[position]
        holder.id = item.id
        holder.title.text = item.title
        holder.content.text = item.context
        holder.writer.text = item.username
        holder.date.text = item.createdAt
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("title", item.title)       // 글 제목 전달
            intent.putExtra("content", item.context)   // 글 내용 전달
            intent.putExtra("writer",item.username)    // 글 작성자 전달
            intent.putExtra("date", item.createdAt)    // 작성 날짜 전달
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = dataList.size
}
