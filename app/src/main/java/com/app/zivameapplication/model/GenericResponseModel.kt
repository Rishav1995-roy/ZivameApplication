package com.app.zivameapplication.model

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import org.json.JSONArray

data class GenericResponseModel(
    @SerializedName("products")
    val products: MutableList<Products> = mutableListOf()
)
