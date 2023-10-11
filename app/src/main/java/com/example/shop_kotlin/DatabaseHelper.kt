package com.example.shop_kotlin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :

    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        // สร้างตารางผู้ใช้
        val createUserTable =
            "CREATE TABLE ${DatabaseContract.UserEntry.TABLE_NAME} (" +
                    "${DatabaseContract.UserEntry.COLUMN_USER_ID} INTEGER PRIMARY KEY," +
                    "${DatabaseContract.UserEntry.COLUMN_USERNAME} TEXT," +
                    "${DatabaseContract.UserEntry.COLUMN_PASSWORD} TEXT)"


        // สร้างตารางสินค้า (สามารถเพิ่มตารางอื่น ๆ ตามต้องการ)
        db?.execSQL(createUserTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // ระบบอัปเกรดฐานข้อมูล เมื่อมีการเปลี่ยนแปลงโครงสร้าง
        if (newVersion > oldVersion) {
            // ตรวจสอบเวอร์ชันเก่าและทำการอัปเกรดตามความเหมาะสม
            when (oldVersion) {
                1 -> {
                    // อัปเกรดจากเวอร์ชัน 1 ไปยังเวอร์ชัน 2
                    // เพิ่มโครงสร้างตารางหรือการเปลี่ยนแปลงอื่น ๆ ที่ต้องการ
                    val sql = "ALTER TABLE ${DatabaseContract.ProductEntry.TABLE_NAME} " +
                            "ADD COLUMN ${DatabaseContract.ProductEntry.COLUMN_IMG_PATH} TEXT"
                    db?.execSQL(sql)
                }
            }
        }
    }


    companion object {
        const val DATABASE_NAME = "myapp.db"
        const val DATABASE_VERSION = 1
    }
}
