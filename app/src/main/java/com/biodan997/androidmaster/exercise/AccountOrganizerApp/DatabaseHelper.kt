package com.biodan997.androidmaster.exercise.AccountOrganizerApp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {


    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(/* sql = */ "CREATE TABLE $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT, PLATFORM TEXT, EMAIL TEXT, NAME TEXT)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME)
        onCreate(db)

    }
/*
    fun insertData(platform: String, email: String, name: String):Boolean {

        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, platform)
        contentValues.put(COL_3, email)
        contentValues.put(COL_4, name)
        db.insert(TABLE_NAME, null, contentValues)
        return true
//EN CASO DE ERROR PERSISTENTE ELIMINAR EL TRUE Y BORRAR EL RECEPTOR BOOLEAN

    }*/
//PRUEBA
    fun insertData(platform: String, email: String, name: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COL_2, platform)
            put(COL_3, email)
            put(COL_4, name)
        }
        val result = db.insert(TABLE_NAME, null, contentValues)
        return result != -1L
    }


    fun updateData(id: String, platform: String, email: String, name: String): Boolean {

        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, id)
        contentValues.put(COL_2, platform)
        contentValues.put(COL_3, email)
        contentValues.put(COL_4, name)
        db.update(TABLE_NAME, contentValues, "ID=?", arrayOf(id))
        return true

    }

    fun deleteData(id: String): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "ID=?", arrayOf(id))

    }

    val allData: Cursor
        get() {
            val db = this.writableDatabase
            val res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
            return res

        }



    companion object {
        val DATABASE_NAME = "ORGANIZER.db"
        val TABLE_NAME = "organizer_table"
        val COL_1 = "ID"
        val COL_2 = "PLATFORM"
        val COL_3 = "EMAIL"
        val COL_4 = "NAME"
    }


}