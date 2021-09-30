package com.app.zivameapplication.webservices

import androidx.lifecycle.MutableLiveData
import com.app.zivameapplication.UtilsWithContext.isNetworkAvailable
import com.google.gson.JsonElement
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call

class ApiService {
    companion object {
        private var statusCode: Int = -1

        fun <ResponseType> makeApiRequest(
            requestCall: Call<ResponseBody>,
            responseClassType: Class<ResponseType>,
            successHandler: OnSuccess<ResponseType>? = null,
            errorHandler: OnException<ResponseType>? = null
        ): MutableLiveData<Response<ResponseType>> {
            val responseLiveData = MutableLiveData<Response<ResponseType>>()
            responseLiveData.value = Response(null, Status.LOADING)

            if (isNetworkAvailable()) {
                val genResponseLiveData = object : GenericRequestHandler<ResponseBody>() {
                    override fun makeRequest() =
                        if (requestCall.isExecuted) requestCall.clone() else requestCall
                }.asLiveData()
                genResponseLiveData.observeForever {
                    if (it.exception == null) {
                        if (it.data is ResponseBody) {
                            val response = it.data
                            val responseBody=ResponseParser<ResponseType>().parseGenericResponseAndGetData(response,responseClassType)
                            responseLiveData.postValue(
                                Response<ResponseType>(
                                    responseBody,
                                    Status.SUCCESS
                                )
                            )
                        }
                    }
                }
            }

            return responseLiveData
        }
    }
}

