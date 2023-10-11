package com.example.shop_kotlin

// ProductListActivity.kt
import DatabaseHelperProduct
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ProductListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val productListView = findViewById<ListView>(R.id.productListView)

        val dbHelper = DatabaseHelperProduct(this)

        // เรียกใช้ฟังก์ชันดึงข้อมูลจากฐานข้อมูล SQLite และสร้างรายการสินค้า
        val productList = dbHelper.getAllProducts()

        val adapter = ProductAdapter(this, productList)
        productListView.adapter = adapter


    }


}

