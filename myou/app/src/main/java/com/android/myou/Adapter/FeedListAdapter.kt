package com.android.myou.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.myou.R

class FeedListAdapter(private val dataList: List<Memo>) :
    RecyclerView.Adapter<FeedListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.note_title)
        val content: TextView = itemView.findViewById(R.id.note_content)
        val writer: TextView = itemView.findViewById(R.id.note_writer)
        val date: TextView = itemView.findViewById(R.id.note_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_memo_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = dataList[position]
        holder.title.text = item.title
        holder.content.text = item.context
        holder.writer.text = item.username
        holder.date.text = item.createdAt
    }

    override fun getItemCount(): Int = dataList.size
}
