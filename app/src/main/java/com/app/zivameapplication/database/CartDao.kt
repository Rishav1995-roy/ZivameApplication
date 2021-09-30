package com.app.zivameapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.zivameapplication.model.CartModel
import com.app.zivameapplication.model.Products

@Dao
interface CartDao {

    @Insert
    fun saveProductsDetails(cartModel: CartModel)

    @Query("DELETE FROM cart_table")
    fun deleteCartTable(): Int

    @Query("SELECT * FROM cart_table")
    fun getAllCartData(): MutableList<CartModel>

}