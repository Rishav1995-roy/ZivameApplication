package com.app.zivameapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.zivameapplication.database.ZivameDatabase
import com.app.zivameapplication.model.CartModel
import com.app.zivameapplication.model.Products

open class BaseActivity:AppCompatActivity() {

    var zivameDatabase: ZivameDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zivameDatabase= ZivameDatabase.getDatabase(this)
    }

    fun addDataToCartTable(products: Products) {
        zivameDatabase?.cartDao?.saveProductsDetails(products)
    }

    fun deleteCartTable(){
        zivameDatabase?.cartDao?.deleteCartTable()
    }

    fun getCartData():MutableList<CartModel>{
        return zivameDatabase?.cartDao?.getAllCartData()!!
    }
}