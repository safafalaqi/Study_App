package com.example.studyapp

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.database.Lesson
import com.example.studyapp.database.StudyAppDB
import com.example.studyapp.databinding.ItemRowBinding

//type key 0= android 1=kotlin
class RecyclerViewAdapter(private var lessons: ArrayList<Lesson>, val typeKey:Int, val context:Context): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)
    private val databaseHelper by lazy{ StudyAppDB(context) }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val t=lessons[position].topic
        val d=lessons[position].detail

        holder.binding.apply {
            tvText.text = t
            detail.text = d
            }
        holder.itemView.setOnClickListener{
            val alert=AlertDialog(context)
            alert.customAlert(lessons[position])
        }

        holder.binding.btimgdel.setOnClickListener{
            delete(lessons[position])
        }
        holder.binding.btimgupdate.setOnClickListener{
            update(position)
        }

    }

    override fun getItemCount()= lessons.size

    fun update(i:Int) {

        val dialog = Dialog(context)
        val dialogview = LayoutInflater.from(context)
            .inflate(R.layout.custom_dialog, null, false)
        //initializing dialog screen
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(true)
        dialog?.setContentView(dialogview)
        dialog?.show()

        val etTopic = dialog.findViewById<EditText>(R.id.etTopic)
        val etDetail = dialog.findViewById<EditText>(R.id.etDetail)
        val btSubmit = dialog.findViewById<Button>(R.id.btUpdate)
        val btClose = dialog.findViewById<AppCompatImageButton>(R.id.imgBtClose)

        etTopic.setText(lessons[i].topic)
        etDetail.setText(lessons[i].detail)

        btSubmit.setOnClickListener {
            if (etTopic.text.isNotBlank() && etDetail.text.isNotBlank()) {
                databaseHelper.updateData(lessons[i].pk, etTopic.text.toString(), etDetail.text.toString(),typeKey)
                lessons=databaseHelper.readData(typeKey)
                notifyDataSetChanged()
                dialog.dismiss()
            } else {
                Toast.makeText(context, "Note can not be empty!", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        }

        btClose.setOnClickListener {
            dialog.cancel()
            notifyDataSetChanged()
        }
    }

    fun delete(lesson: Lesson){
        val dialogBuilder = android.app.AlertDialog.Builder(context)
        dialogBuilder.setMessage("Are you sure you want to delete the note?")
            // negative button text and action
            .setPositiveButton("yes", DialogInterface.OnClickListener {
                    dialog, id ->
                databaseHelper.deleteData(lesson,typeKey)
                lessons=databaseHelper.readData(typeKey)
                notifyDataSetChanged()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
                notifyDataSetChanged()
            })
        // create dialog box
        val alert = dialogBuilder.create()
        // show alert dialog
        alert.show()
    }

    fun updateRV(lessonsList:ArrayList<Lesson>){
        lessons=lessonsList
        notifyDataSetChanged()
    }
}
