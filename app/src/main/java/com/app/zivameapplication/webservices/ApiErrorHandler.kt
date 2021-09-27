package com.app.zivameapplication.webservices

import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

/**
 * ApiErrorHandler class should be extended to handle custom exceptions
 **/
open class ApiErrorHandler {


    /**
     *  Method should be overridden to return custom exception type which
     *  would be a sub-type of Exception or to have the response body,
     *  return a sub-type of kexception()
     */
    open fun getExceptionType(response: retrofit2.Response<*>): GenericException {
        var message = ""
        var errorJson: String? = ""
        try {
            if (response.errorBody() != null) {
                errorJson = response.errorBody()?.string()
                val jObjError = JSONObject(errorJson!!)
                message = jObjError.optString("message")
            }
        } catch (e1: JSONException) {
            e1.printStackTrace()
        } catch (e1: IOException) {
            e1.printStackTrace()
        }

        return GenericException(
            message,
            null,
            errorJson = errorJson?: ""
        )
    }


}