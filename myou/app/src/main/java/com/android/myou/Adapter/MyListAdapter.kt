package com.android.myou.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.myou.App
import com.android.myou.R
import com.android.myou.api.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyListAdapter (private val dataList: List<Memo>) :
    RecyclerView.Adapter<MyListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.note_title)
        val content: TextView = itemView.findViewById(R.id.note_content)
        val date: TextView = itemView.findViewById(R.id.note_date)
        val deleteBtn : ImageView = itemView.findViewById(R.id.delete_memo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_my_memo_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = dataList[position]
        holder.title.text = item.title
        holder.content.text = item.context
        holder.date.text = item.createdAt
        holder.deleteBtn.setOnClickListener {
            deleteMemo(id = 1)
        }
    }

    fun deleteMemo(id : Int) {
        val apiService = RetrofitClient.apiservice
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.deleteDiary(id)
                Log.e("API Response", response.toString())
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }

    override fun getItemCount(): Int = dataList.size
}