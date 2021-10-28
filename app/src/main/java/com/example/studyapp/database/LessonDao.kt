package com.example.studyapp.database

import androidx.room.*

@Dao
interface LessonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLesson(note: Lesson)

    @Query("SELECT * FROM Lessons WHERE type= :type")
    fun getLessons(type:Int): List<Lesson>

    @Update
    suspend fun updateLesson(note: Lesson)

    @Delete
    suspend fun deleteLesson(note: Lesson)
}