package com.example.test_app.network

import android.content.Context
import com.example.test_app.R
import com.google.gson.Gson
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ApiErrorUtil {
    fun getErrorMessage(t: Throwable, context: Context?): String {
        if (context == null) return ""
        val apiError = getError(t)
        val errorMessage: String
        errorMessage = when {
            apiError == null -> context.getString(R.string.api_error)
            apiError.code == ErrorCodes.NO_INTERNET -> context.getString(R.string.no_internet)
            apiError.code == ErrorCodes.TIME_OUT -> context.getString(R.string.timeout)
            apiError.detail.isNotBlank() -> apiError.detail
            else -> context.getString(R.string.api_error)
        }
        return errorMessage
    }

    private fun getError(t: Throwable): DefaultResponse? {
        var apiError: DefaultResponse? = null
        when (t) {
            is SocketTimeoutException -> apiError =
                DefaultResponse(
                    ErrorCodes.TIME_OUT,
                    ""
                )
            is UnknownHostException -> apiError =
                DefaultResponse(
                    ErrorCodes.NO_INTERNET,
                    ""
                )
            is HttpException -> try {
                apiError = Gson().fromJson(t.response()?.errorBody()?.string() ?: "", DefaultResponse::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return apiError
    }

    object ErrorCodes {
        const val TIME_OUT = "TIME_OUT"
        const val NO_INTERNET = "NO_INTERNET"
    }
}