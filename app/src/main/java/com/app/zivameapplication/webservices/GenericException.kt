package com.app.zivameapplication.webservices

import okhttp3.ResponseBody

/**
 * Generic Exception class with data and error body
 */
open class GenericException(
    message: String?,
    cause: Throwable?,
    var errorJson: String
) : Exception(message, cause)