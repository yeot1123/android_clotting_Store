package com.example.shop_kotlin

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerUsernameEditText: EditText
    private lateinit var registerPasswordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        dbHelper = DatabaseHelper(this)

        registerUsernameEditText = findViewById(R.id.registerUsernameEditText)
        registerPasswordEditText = findViewById(R.id.registerPasswordEditText)
        registerButton = findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            val username = registerUsernameEditText.text.toString()
            val password = registerPasswordEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                if (registerUser(username, password)) {
                    Toast.makeText(this, "สมัครสมาชิกสำเร็จ", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "มีชื่อผู้ใช้นี้อยู่แล้ว", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(username: String, password: String): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put(DatabaseContract.UserEntry.COLUMN_USERNAME, username)
        values.put(DatabaseContract.UserEntry.COLUMN_PASSWORD, password)

        // ตรวจสอบว่ามีชื่อผู้ใช้นี้ในฐานข้อมูลแล้วหรือไม่
        val cursor = db.query(
            DatabaseContract.UserEntry.TABLE_NAME,
            arrayOf(DatabaseContract.UserEntry.COLUMN_USERNAME),
            "${DatabaseContract.UserEntry.COLUMN_USERNAME} = ?",
            arrayOf(username),
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            cursor.close()
            return false  // ชื่อผู้ใช้นี้มีอยู่แล้ว
        } else {
            val newRowId = db.insert(DatabaseContract.UserEntry.TABLE_NAME, null, values)
            cursor.close()
            return newRowId != -1L
        }
    }
}
