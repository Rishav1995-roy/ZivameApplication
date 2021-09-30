package com.app.zivameapplication.repository

import androidx.lifecycle.MutableLiveData
import com.app.zivameapplication.model.GenericResponseModel
import com.app.zivameapplication.model.Products
import com.app.zivameapplication.resettableLazy
import com.app.zivameapplication.resettableManager
import com.app.zivameapplication.webservices.ApiService
import com.app.zivameapplication.webservices.Response
import com.app.zivameapplication.webservices.RetrofitBuilder

object HomeRepo {

    val lazyMgr = resettableManager()
    private val homeRequests: HomeRequests by resettableLazy(lazyMgr) {
        RetrofitBuilder.build().create(HomeRequests::class.java)
    }

    fun getGadgetsData(): MutableLiveData<Response<GenericResponseModel>> {
        return ApiService.makeApiRequest(
            homeRequests.getGadgetsData(),
            GenericResponseModel::class.java
        )
    }
}