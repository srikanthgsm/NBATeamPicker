package com.nbateampicker.data.api

import com.nbateampicker.data.entity.ErrorResponse
import com.google.gson.Gson
import retrofit2.Response

/**
 * Common class used by API responses.
 * @param <T> the type of the response object
</T> */
@Suppress("unused") // T is used in extending classes
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {

            return ApiErrorResponse(
                ErrorResponse(
                    "exception",
                    error.message ?: "unknown error",
                    "", "", ""
                )
            )
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body = body)
                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                val errorRes: ErrorResponse = try {
                    Gson().fromJson<ErrorResponse>(errorMsg, ErrorResponse::class.java)
                } catch (e: Exception) {
                    e.printStackTrace()
                    ErrorResponse(
                        response.code().toString(),
                        errorMsg ?: "unknown error",
                        "", "", ""
                    )
                }
                ApiErrorResponse(
                    errorRes
                )
            }
        }
    }
}

/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

data class ApiErrorResponse<T>(val error: ErrorResponse) : ApiResponse<T>()
