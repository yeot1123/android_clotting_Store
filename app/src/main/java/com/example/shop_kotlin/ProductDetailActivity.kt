package com.example.shop_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ProductDetailActivity : AppCompatActivity() {


    private var quantity = 1 // จำนวนเริ่มต้น
    private var productPrice: Double = 0.0 // ราคาสินค้า
    private var totalAmount: Double = 0.0 // ราคารวม

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


        // อ้างอิงไปยังองค์ประกอบใน layout
        val increaseButton = findViewById<Button>(R.id.increaseButton)
        val decreaseButton = findViewById<Button>(R.id.decreaseButton)
        val quantityTextView = findViewById<TextView>(R.id.quantityTextView)
        val totalPriceTextView = findViewById<TextView>(R.id.totalPriceTextView)


        // กำหนดราคารวมเริ่มต้น
        totalAmount = productPrice

        // แสดงค่าเริ่มต้น
        quantityTextView.text = quantity.toString()
        totalPriceTextView.text = "Total: $$totalAmount"

        increaseButton.setOnClickListener {
            // เพิ่มจำนวนและคำนวณราคารวม
            quantity++
            totalAmount = productPrice * quantity
            quantityTextView.text = quantity.toString()
            totalPriceTextView.text = "Total: $$totalAmount"
        }

        decreaseButton.setOnClickListener {
            // ลดจำนวนและคำนวณราคารวม
            if (quantity > 1) {
                quantity--
                totalAmount = productPrice * quantity
                quantityTextView.text = quantity.toString()
                totalPriceTextView.text = "Total: $$totalAmount"
            }
        }
    }
}