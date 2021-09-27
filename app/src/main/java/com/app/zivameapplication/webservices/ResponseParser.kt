package com.app.zivameapplication.webservices

import com.google.gson.Gson

class ResponseParser<T> {

    fun parseGenericResponseAndGetData(
        response: String,
        classType: Class<T>
    ): T {
        return Gson().fromJson(response, classType)
    }

}