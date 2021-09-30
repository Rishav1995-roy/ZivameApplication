package com.app.zivameapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class CartModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id:Int=0,

    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name="price")
    var price:String,
    @ColumnInfo(name="imageUrl")
    var imageUrl:String,
    @ColumnInfo(name="rating")
    var rating:Int,

)
