package com.app.zivameapplication

import android.content.Context
import android.net.ConnectivityManager


object UtilsWithContext {

    private lateinit var context: Context
    var onRetryClick: () -> Unit = {}

    fun init(context: Context) {
        UtilsWithContext.context = context
    }

    fun getContext(): Context {
        return context
    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun showNetworkErrorActivity(
        isNetwork: Boolean,
        errorMessage: String? = context.getString(R.string.default_error),
        onRetryClick: () -> Unit = {}
    ) {
        if (!isNetwork && errorMessage.isNullOrEmpty()) {// Even for internet unavailable, it will throw failure
            return
        }
        UtilsWithContext.onRetryClick = onRetryClick
    }
}