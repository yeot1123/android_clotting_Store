package com.example.shop_kotlin

// ProductAdapter.kt
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.squareup.picasso.Picasso

class ProductAdapter(private val context: Context, private var productList: List<Product>, private val deleteListener: ProductDeleteListener) : BaseAdapter() {

    override fun getCount(): Int {
        return productList.size
    }

    override fun getItem(position: Int): Any {
        return productList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)


        val productImageView = view.findViewById<ImageView>(R.id.productImageView)
        val productNameTextView = view.findViewById<TextView>(R.id.productNameTextView)
        val productPriceTextView = view.findViewById<TextView>(R.id.productPriceTextView)
        val productDescriptionTextView = view.findViewById<TextView>(R.id.productDescriptionTextView)
        val btnDelete = view.findViewById<Button>(R.id.btnDelete)
        val product = productList[position]

        // นำข้อมูลมาแสดงผลใน View
        productNameTextView.text = product.name
        productPriceTextView.text = "Price: $${product.price}"
        productDescriptionTextView.text = product.description

        // นำ URL ของรูปภาพมาแสดง
        // ในที่นี้คุณสามารถใช้ Glide หรือ Picasso เป็นไลบรารีสำหรับการแสดงรูปภาพ
        Picasso.get().load(product.imagePath).into(productImageView)


        btnDelete.setOnClickListener {
            // เรียกฟังก์ชันลบสินค้าที่ต้องการ
            deleteListener.onDeleteProduct(product)
            // คำสั่งนี้ควรถูกนำไปใช้ใน ProductListActivity
        }

        return view

    }
    interface ProductDeleteListener {
        fun onDeleteProduct(product: Product)

    }
    fun updateProductList(updatedList: List<Product>) {
        productList = updatedList
        notifyDataSetChanged()
    }



}

