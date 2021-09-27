package com.app.zivameapplication.webservices

/**
 * Holds the response and the status
 */
open class Response<ResponseType>(
    var data: ResponseType?,
    var status: Status,
)

/**
 * Status of the data fetching process
 */
enum class Status {
    SUCCESS, FAILURE, LOADING
}