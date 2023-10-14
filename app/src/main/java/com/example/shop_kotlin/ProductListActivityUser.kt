package com.example.shop_kotlin

// ProductListActivity.kt
import DatabaseHelperProduct
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ProductListActivityUser : AppCompatActivity(), ProductAdapterUser.ProductSelectionListener {

    private lateinit var productList: List<Product> // ประกาศตัวแปร productList ในระดับคลาส
    private lateinit var productListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list_user)

        productListView = findViewById(R.id.productListViewUser)

        val dbHelper = DatabaseHelperProduct(this)
        productList = dbHelper.getAllProducts() // ให้ productList เก็บข้อมูลจากฐานข้อมูล

        val adapter = ProductAdapterUser(this, productList, this)
        productListView.adapter = adapter
    }

    override fun onProductSelected(product: Product) {
        // สร้าง Intent เพื่อเปิด Activity ยืนยันออร์เดอร์ หรือหน้าอื่น
        val intent = Intent(this, ProductDetailActivity::class.java)

        // ส่งข้อมูลรายการสินค้าผ่าน Intent
        intent.putExtra("productImage", product.imagePath) // URL รูปภาพ
        intent.putExtra("productName", product.name) // ชื่อสินค้า
        intent.putExtra("productPrice", product.price) // ราคา
        intent.putExtra("productDescription", product.description) // รายละเอียด

        startActivity(intent)
    }
}








