package com.app.zivameapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.zivameapplication.database.ZivameDatabase
import com.app.zivameapplication.model.CartModel

open class BaseActivity:AppCompatActivity() {

    var zivameDatabase: ZivameDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zivameDatabase= ZivameDatabase.getDatabase(this)
        UtilsWithContext.init(this)
    }

    fun addDataToCartTable(cartModel: CartModel) {
        zivameDatabase?.cartDao?.saveProductsDetails(cartModel)
    }

    fun deleteCartTable(){
        zivameDatabase?.cartDao?.deleteCartTable()
    }

    fun getCartData():MutableList<CartModel>{
        return zivameDatabase?.cartDao?.getAllCartData()!!
    }
}