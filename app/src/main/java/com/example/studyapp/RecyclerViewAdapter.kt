package com.example.a2in1app

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.AlertDialog
import com.example.studyapp.databinding.ItemRowBinding


class RecyclerViewAdapter(private val topic: Array<String>, private val detail: Array<String>,val context:Context): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val t=topic[position]
        val d=detail[position]
        holder.binding.apply {
            tvText.text = t
            detail.text = d
            }
        holder.itemView.setOnClickListener{
            val alert=AlertDialog(context)
            alert.customAlert(t,d)
        }
    }

    override fun getItemCount()= topic.size


}