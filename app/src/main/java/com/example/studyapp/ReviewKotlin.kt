package com.example.studyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a2in1app.RecyclerViewAdapter

class ReviewKotlin : AppCompatActivity() {
    private lateinit var myRv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_kotlin)
        setTitle("Kotlin Review")
        myRv = findViewById<RecyclerView>(R.id.rvKotlin)
        val alert=AlertDialog(this)
        var topic= getResources().getStringArray(R.array.kotlin_topic)

        var detail= getResources().getStringArray(R.array.kotlin_detail)
        myRv.adapter = RecyclerViewAdapter(topic,detail,this)
        myRv.layoutManager = LinearLayoutManager(this)


    }
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