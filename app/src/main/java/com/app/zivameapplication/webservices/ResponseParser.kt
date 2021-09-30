package com.app.zivameapplication.webservices

import com.google.gson.Gson
import okhttp3.ResponseBody

class ResponseParser<T> {

    fun parseGenericResponseAndGetData(
        response: ResponseBody,
        classType: Class<T>
    ): T {
        return Gson().fromJson(response.string(), classType)
    }

}