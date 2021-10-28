package com.example.studyapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.database.Lesson
import com.example.studyapp.database.LessonDatabase
import com.example.studyapp.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReviewKotlin : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myRv: RecyclerView
    private lateinit var rvAdapter: RecyclerViewAdapter
    lateinit var kotlinList:List<Lesson>
    private val lessonDao by lazy{ LessonDatabase.getInstance(this).lessonDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_kotlin)

        title = "Kotlin Review"
        myRv = findViewById(R.id.rvKotlin)

        CoroutineScope(Dispatchers.IO).launch {
            kotlinList = lessonDao.getLessons(1)
            withContext(Dispatchers.Main) {
                rvAdapter = RecyclerViewAdapter(kotlinList, 1, this@ReviewKotlin)
                myRv.adapter = rvAdapter
                myRv.layoutManager = LinearLayoutManager(this@ReviewKotlin)
            }
        }
        val fabAndroid=findViewById<FloatingActionButton>(R.id.fbKotlin)
        fabAndroid.setOnClickListener{
            addDialog()
        }
    }

    private fun addDialog() {
        val dialog = Dialog(this)
        val dialogview = LayoutInflater.from(this)
            .inflate(R.layout.custom_dialog, null, false)
        //initializing dialog screen
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(true)
        dialog?.setContentView(dialogview)
        dialog?.show()

        val etTopic = dialog.findViewById<EditText>(R.id.etTopic)
        val etDeatil = dialog.findViewById<EditText>(R.id.etDetail)
        val btSubmit = dialog.findViewById<Button>(R.id.btUpdate)
        val btClose =dialog.findViewById<AppCompatImageButton>(R.id.imgBtClose)

        btSubmit.setOnClickListener{
            if(etTopic.text.isNotBlank() && etDeatil.text.isNotBlank()){
                CoroutineScope(Dispatchers.IO).launch {
                    lessonDao.addLesson(
                        Lesson(
                            0,
                            etTopic.text.toString(),
                            etDeatil.text.toString(),
                            1
                        )
                    )
                    kotlinList = lessonDao.getLessons(1)
                    withContext(Dispatchers.Main) {
                        rvAdapter.updateRV(kotlinList)
                        dialog.dismiss()
                    }
                }
            }else
                Toast.makeText(this," can not be empty!", Toast.LENGTH_SHORT).show()


            btClose.setOnClickListener{
                dialog.cancel()
            }
        }}
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.kotlinreview -> {
                val intent = Intent(this, ReviewKotlin::class.java)
                startActivity(intent)
                return true
            }
            R.id.androidreview -> {
                val intent = Intent(this, ReviewAndroid::class.java)
                startActivity(intent)
                return true
            }
            R.id.clMain -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
