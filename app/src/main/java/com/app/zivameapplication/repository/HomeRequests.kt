package com.app.zivameapplication.repository

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface HomeRequests {

    @GET("/nancymadan/assignment/db")
    fun getGadgetsData(): Call<ResponseBody>
}