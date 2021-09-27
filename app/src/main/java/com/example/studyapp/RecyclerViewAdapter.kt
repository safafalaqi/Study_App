package com.example.a2in1app

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.R
import kotlinx.android.synthetic.main.item_row.view.*


class RecyclerViewAdapter(private val topic: ArrayList<String>,private val pref: ArrayList<String>,private val detail: ArrayList<String>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val t=topic[position]
        val d=detail[position]
        val p=pref[position]
        holder.itemView.apply{
            tvText.text = t
            detail.text = d
        }
    }

    override fun getItemCount()= topic.size
}