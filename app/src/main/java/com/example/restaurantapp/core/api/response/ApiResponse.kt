package com.example.restaurantapp.core.api.response

import com.example.restaurantapp.core.dto.ErrorDto
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
                ErrorDto(message = error.message ?: "unknown error")
            )
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(
                        body = body
                    )
                }
            } else {
                ApiErrorResponse(
                    Gson().fromJson(response.errorBody()?.string(), ErrorDto::class.java)
                )
            }
        }
    }
}

/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(
    val body: T
) : ApiResponse<T>()

data class ApiErrorResponse<T>(val error: ErrorDto) : ApiResponse<T>()
