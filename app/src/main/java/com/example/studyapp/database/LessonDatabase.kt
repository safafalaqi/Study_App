package com.example.studyapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Lesson::class],version = 1,exportSchema = false)
abstract class LessonDatabase: RoomDatabase() {

    companion object{
        @Volatile
        private var INSTANCE: LessonDatabase? = null

        fun getInstance(context: Context):LessonDatabase {
            if (INSTANCE != null) {
                return INSTANCE as LessonDatabase
            }
            synchronized(this){  //for the protection purpose from concurrent execution on multi threading
                val instance = Room.databaseBuilder(context.applicationContext, LessonDatabase::class.java, "lessons"
                ).fallbackToDestructiveMigration()  // Destroys old database on version change
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    abstract fun lessonDao(): LessonDao

}