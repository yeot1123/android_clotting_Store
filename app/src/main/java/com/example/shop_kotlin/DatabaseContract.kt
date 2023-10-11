package com.example.shop_kotlin

import android.provider.BaseColumns

object DatabaseContract {
    // ตาราง User
    class UserEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "users"
            const val COLUMN_USER_ID = "custid"
            const val COLUMN_USERNAME = "username"
            const val COLUMN_PASSWORD = "password"
        }
    }

    // ตาราง Product (สามารถเพิ่มตารางอื่น ๆ ตามต้องการ)
    class ProductEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "products"
            const val COLUMN_PRODUCT_ID = "productid"
            const val COLUMN_NAME = "name"
            const val COLUMN_PRICE = "price"
            const val COLUMN_IMG = "image"
            const val COLUMN_IMG_PATH = "image_path"
            // เพิ่มคอลัมน์อื่น ๆ ตามต้องการ
        }
    }
}
