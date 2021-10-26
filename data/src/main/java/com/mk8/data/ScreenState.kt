package com.mk8.data

sealed class ScreenState<out T, out ERROR> {
    object Loading : ScreenState<Nothing, Nothing>()
    data class Success<T>(val data: T) : ScreenState<T, Nothing>()
    data class Error<ERROR>(val errorMessage: ERROR) : ScreenState<Nothing, ERROR>()
}