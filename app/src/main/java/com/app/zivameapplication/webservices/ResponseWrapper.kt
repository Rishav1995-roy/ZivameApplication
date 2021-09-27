package com.app.zivameapplication.webservices

/**
 *    ResponseWrapper for Api responses
 *   @param exception Returns thrown exception or null if successful request
 *   @param data Returns response object or null if empty object or not successful
 *   @param statusCode Returns the request state or -1 if request does not happen due to internet
 *   or similar connectivity issues
 */
class ResponseWrapper<T>(
    val exception: Exception? = null,
    val data: T? = null,
    val statusCode: StatusCode,
    val errorJson: String = ""
)

/**
 *  API response status code
 */
class StatusCode(val code: Int)