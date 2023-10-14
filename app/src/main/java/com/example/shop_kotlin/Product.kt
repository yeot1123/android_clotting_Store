package com.example.shop_kotlin

data class Product(
    val id: Long,
    val name: String,
    val price: Double,
    val description: String,
    val imagePath: String // ระบุ URL ของรูปภาพหรือเรียกใช้รูปภาพจากการเก็บในระบบไฟล์
)
