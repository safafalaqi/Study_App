package com.example.studyapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Lessons")
data class Lesson(
    @PrimaryKey(autoGenerate = true)
    val pk: Int,
    var topic:String,
    var detail:String,
    val type:Int) {
}