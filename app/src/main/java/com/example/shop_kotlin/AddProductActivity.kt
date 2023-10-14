package com.example.shop_kotlin


import DatabaseHelperProduct
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso


class AddProductActivity : AppCompatActivity() {

    private lateinit var etProductName: EditText
    private lateinit var etProductPrice: EditText
    private lateinit var etProductDescription: EditText
    private lateinit var etProductUrl: EditText

    private lateinit var ivProductImage: ImageView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        val dbHelper = DatabaseHelperProduct(this)

        etProductName = findViewById(R.id.etProductName)
        etProductPrice = findViewById(R.id.etProductPrice)
        etProductDescription = findViewById(R.id.etProductDescription)
        etProductUrl = findViewById(R.id.etProductUrl)
        ivProductImage = findViewById(R.id.ivProductImage)

        val btnAddProduct: Button = findViewById(R.id.btnAddProduct)
        btnAddProduct.setOnClickListener {
            // เมื่อคลิกที่ปุ่ม "เพิ่มสินค้า"
            val productName = etProductName.text.toString()
            val productPrice = etProductPrice.text.toString().toDoubleOrNull() ?: 0.0
            val productDescription = etProductDescription.text.toString()
            val productUrl = etProductUrl.text.toString()

            val product = Product(0,productName, productPrice, productDescription, productUrl)

            dbHelper.addProduct(product) // เรียกใช้ฟังก์ชันเพิ่มสินค้า

            // หลังจากบันทึกข้อมูลสินค้าเสร็จสิ้น คุณสามารถปิด Activity นี้

        }
        val btnLoadImageFromUrl = findViewById<Button>(R.id.btnLoadImageFromUrl)
        btnLoadImageFromUrl.setOnClickListener {
            // อ่าน URL จาก EditText
            val imageUrl = etProductUrl.text.toString()

            // ใช้ Picasso หรือ Glide เพื่อโหลดและแสดงรูปภาพจาก URL ใน ImageView
            if (imageUrl.isNotEmpty()) {
                Picasso.get().load(imageUrl).into(ivProductImage)
            }
        }

    }


}

