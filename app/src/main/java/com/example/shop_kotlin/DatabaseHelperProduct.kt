import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.shop_kotlin.Product

class DatabaseHelperProduct(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "product.db"

        // ชื่อตารางสินค้าและชื่อคอลัมน์
        private const val TABLE_PRODUCTS = "products"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_PRICE = "price"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_IMAGE_PATH = "image_path"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE $TABLE_PRODUCTS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_PRICE REAL," +
                "$COLUMN_DESCRIPTION TEXT," +
                "$COLUMN_IMAGE_PATH TEXT)")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // การอัพเกรดฐานข้อมูล (ไม่ได้ใช้ในตัวอย่างนี้)
    }

    fun getAllProducts(): List<Product> {
        val productList = mutableListOf<Product>()

        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_PRODUCTS"
        val cursor: Cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val productId = cursor.getLong(cursor.getColumnIndex(COLUMN_ID))
                val productName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                val productPrice = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE))
                val productDescription = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                val productImageUrl = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_PATH))

                val product = Product(productId,productName, productPrice, productDescription, productImageUrl)
                productList.add(product)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return productList
    }

    fun addProduct(product: Product) {
        val values = ContentValues()
        values.put(COLUMN_NAME, product.name)
        values.put(COLUMN_PRICE, product.price)
        values.put(COLUMN_DESCRIPTION, product.description)
        values.put(COLUMN_IMAGE_PATH, product.imagePath)

        val db = this.writableDatabase
        db.insert(TABLE_PRODUCTS, null, values)
        db.close()
    }

    fun deleteProduct(product: Long) {
        val db = this.writableDatabase
        db.delete(TABLE_PRODUCTS, "$COLUMN_ID=?", arrayOf(product.toString()))
        db.close()
    }



}
