package com.didi.core.repository

sealed class Outcome<out T, out E> {
    data class Success<out T>(val data: T) : Outcome<T, Nothing>()
    data class Error<out E>(val error: E) : Outcome<Nothing, E>()
}