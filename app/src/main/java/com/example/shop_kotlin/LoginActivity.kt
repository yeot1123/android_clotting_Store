package com.example.shop_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var regisbutton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        regisbutton = findViewById(R.id.regisbutton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // ตรวจสอบการล็อกอิน
            if (isValidCredentials(username, password)) {
                // ถ้าล็อกอินสำเร็จ ให้ไปยังหน้าหลัก
                val intent = Intent(this, ProductListActivityUser::class.java)
                startActivity(intent)
            } else if (username == "admin" && password == "123") {
                // ถ้าล็อกอินไม่สำเร็จ ให้แจ้งเตือน
                val intent = Intent(this, AddProductActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "ล็อกอินไม่สำเร็จ", Toast.LENGTH_SHORT).show()

            }
        }
        regisbutton.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.readableDatabase
        val selection = "${DatabaseContract.UserEntry.COLUMN_USERNAME} = ? AND " +
                "${DatabaseContract.UserEntry.COLUMN_PASSWORD} = ?"
        val selectionArgs = arrayOf(username, password)

        val cursor = db.query(
            DatabaseContract.UserEntry.TABLE_NAME,
            arrayOf(DatabaseContract.UserEntry.COLUMN_USER_ID),
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val isValid = cursor.moveToFirst()
        cursor.close()
        return isValid
    }

}
