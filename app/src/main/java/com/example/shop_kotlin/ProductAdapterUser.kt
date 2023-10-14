package com.example.shop_kotlin

// ProductAdapter.kt
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.squareup.picasso.Picasso

class ProductAdapterUser(private val context: Context, private var productListUser: List<Product>, private val selectionListener: ProductSelectionListener) : BaseAdapter() {

    interface ProductSelectionListener {
        fun onProductSelected(product: Product)
    }
    override fun getCount(): Int {
        return productListUser.size
    }

    override fun getItem(position: Int): Any {
        return productListUser[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_product_user, parent, false)


        val productImageView = view.findViewById<ImageView>(R.id.productImageView)
        val productNameTextView = view.findViewById<TextView>(R.id.productNameTextView)
        val productPriceTextView = view.findViewById<TextView>(R.id.productPriceTextView)
        val productDescriptionTextView = view.findViewById<TextView>(R.id.productDescriptionTextView)
        val product = productListUser[position]

        // นำข้อมูลมาแสดงผลใน View
        productNameTextView.text = product.name
        productPriceTextView.text = "Price: $${product.price}"
        productDescriptionTextView.text = product.description

        // นำ URL ของรูปภาพมาแสดง
        // ในที่นี้คุณสามารถใช้ Glide หรือ Picasso เป็นไลบรารีสำหรับการแสดงรูปภาพ
        Picasso.get().load(product.imagePath).into(productImageView)

        view.setOnClickListener {
            // เรียกฟังก์ชันเลือกรายการสินค้าผ่านอินเทอร์เฟซ
            selectionListener.onProductSelected(product)
        }


        return view

    }


}

