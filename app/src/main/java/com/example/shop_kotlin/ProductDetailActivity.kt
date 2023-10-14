package com.example.shop_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val productImageView = findViewById<ImageView>(R.id.productImageView)
        val productNameTextView = findViewById<TextView>(R.id.productNameTextView)
        val productPriceTextView = findViewById<TextView>(R.id.productPriceTextView)
        val productDescriptionTextView = findViewById<TextView>(R.id.productDescriptionTextView)

        // รับข้อมูลรายการสินค้าที่ถูกส่งมาจาก Intent
        val productImage = intent.getStringExtra("productImage")
        val productName = intent.getStringExtra("productName")
        val productPrice = intent.getDoubleExtra("productPrice", 0.0)
        val productDescription = intent.getStringExtra("productDescription")

        // ใช้ข้อมูลรายการสินค้าในการแสดงข้อมูลในหน้า Activity
        Picasso.get().load(productImage).into(productImageView)
        productNameTextView.text = productName
        productPriceTextView.text = "Price: $${productPrice}"
        productDescriptionTextView.text = productDescription
    }
}