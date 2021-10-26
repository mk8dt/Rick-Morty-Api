package com.mk8.pruebarickmorty.config.network

import com.mk8.data.Either
import com.mk8.data.value
import com.mk8.data.wrong
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkController {

    fun checkException(exception: Exception): Either<String, Nothing> {
        return when (exception) {
            is UnknownHostException -> wrong(ErrorController.networkDown)
            is SocketTimeoutException -> wrong(ErrorController.timeOut)
            is NullPointerException -> wrong(ErrorController.notFound)
            else -> wrong(ErrorController.errorGeneral)
        }
    }

    fun <T : Any> checkResponse(response: Response<T>): Either<String, T> {

        return when (response.code()) {

            in 200..299 -> if (response.body() != null) {
                value(response.body() as T)
            } else wrong(response.errorBody().toString())

            else -> wrong(ErrorController.checkError(response.code()))
        }
    }
}