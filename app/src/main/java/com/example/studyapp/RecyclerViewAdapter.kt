package com.example.a2in1app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.AlertDialog
import com.example.studyapp.R
import kotlinx.android.synthetic.main.item_row.view.*


class RecyclerViewAdapter(private val topic: Array<String>, private val detail: Array<String>,val context:Context): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
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
        holder.itemView.apply{
            tvText.text = t
            detail.text = d
            }
        holder.itemView.setOnClickListener{
            val alert=AlertDialog(context)
            alert.customAlert(t,d)
        }
    }

    override fun getItemCount()= topic.size

    /*fun bind(topic:String,detail:String,clickListener: AdapterView.OnItemClickListener)
    {
        tvText.text = topic
        detail.text = detail

        itemView.setOn

    }*/
}