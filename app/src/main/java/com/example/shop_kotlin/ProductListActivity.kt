package com.example.shop_kotlin

// ProductListActivity.kt
import DatabaseHelperProduct
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ProductListActivity : AppCompatActivity() {
    private lateinit var productList: List<Product>
    private lateinit var productListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        productListView = findViewById(R.id.productListView)

        val dbHelper = DatabaseHelperProduct(this)
        productList = dbHelper.getAllProducts()

        val adapter = ProductAdapter(this, productList, object : ProductAdapter.ProductDeleteListener {
            override fun onDeleteProduct(product: Product) {
                deleteProduct(product)
            }
        })
        productListView.adapter = adapter
    }

    private fun deleteProduct(product: Product) {
        val dbHelper = DatabaseHelperProduct(this)
        dbHelper.deleteProduct(product.id)

        // อัปเดตรายการสินค้า
        val updatedProductList = dbHelper.getAllProducts()
        (productListView.adapter as ProductAdapter).updateProductList(updatedProductList)
    }



}







