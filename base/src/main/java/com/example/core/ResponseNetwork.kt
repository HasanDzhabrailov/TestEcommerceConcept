package com.example.core

sealed class ResponseNetwork<T> {
	class Success<T>(val data: T) : ResponseNetwork<T>()
	class Error<T>(val message: String?) : ResponseNetwork<T>()
}