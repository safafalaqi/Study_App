package com.example.studyapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.lang.Exception

class StudyAppDB (val context: Context): SQLiteOpenHelper(context,"android.db",null,1) {
    var sqLiteDatabase: SQLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {
        if(db!=null){
            db.execSQL("CREATE TABLE android (_id integer PRIMARY KEY autoincrement,Topic text, Detail text)")
            db.execSQL("CREATE TABLE kotlin (_id integer PRIMARY KEY autoincrement,Topic text, Detail text)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS android")
        db!!.execSQL("DROP TABLE IF EXISTS kotlin")
        onCreate(db)
    }
    //0 for android 1 for kotlin
    fun saveData(topic: String,detail: String, type:Int): Long {
        val cv = ContentValues()
        cv.put("Topic", topic)
        cv.put("Detail", detail)

        if(type==0)
        return sqLiteDatabase.insert("android", null, cv)
        else
       return sqLiteDatabase.insert("kotlin", null, cv)

    }

    fun readData(type:Int): ArrayList<Lesson> {
        val lList= ArrayList<Lesson>()
        var cursor: Cursor? =null
        if(type==0)
        cursor= sqLiteDatabase.rawQuery("SELECT * FROM android",null)
        else
        cursor= sqLiteDatabase.rawQuery("SELECT * FROM kotlin",null)

        if(cursor.count<1)
        {
           return lList
        }else
        {
            while(cursor.moveToNext())
            {
                val pk = cursor.getInt(0)
                val topic = cursor.getString(1)
                val detail= cursor.getString(2)

                lList.add(Lesson(pk,topic,detail))
            }
        }
        return lList
    }

    fun updateData(pk: Int, topic: String,detail:String,type: Int){

        try {
            val cv=ContentValues()
            cv.put("Topic",topic)
            cv.put("Detail",detail)
            var status=-1

            if(type==0)
            status= sqLiteDatabase.update("android",cv,"_id = $pk",null)
            else
            status= sqLiteDatabase.update("kotlin",cv,"_id = $pk",null)

            Toast.makeText(context, "Update success! $status", Toast.LENGTH_SHORT)
                .show()

        }catch (e: Exception){
            Toast.makeText(context,"Can not Update! ",  Toast.LENGTH_SHORT  ).show()

        }
    }

    fun deleteData(lesson: Lesson, type: Int) {
        try {
            if(type==0)
            sqLiteDatabase.delete("android","_id = ${lesson.pk}",null)
            else
            sqLiteDatabase.delete("kotlin","_id = ${lesson.pk}",null)

            Toast.makeText(context,"Delete success! ",  Toast.LENGTH_SHORT  ).show()
        }catch (e: Exception){
            Toast.makeText(context,"Can not delete! ",  Toast.LENGTH_SHORT  ).show()
        }
    }
}