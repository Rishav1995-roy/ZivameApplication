package com.app.zivameapplication.model

import com.google.gson.annotations.SerializedName

data class Products(
@SerializedName("name")
val name: String,
@SerializedName("price")
val price: String,
@SerializedName("image_url")
val image_url: String,
@SerializedName("rating")
val rating: Int,
)
