package com.example.core

sealed class ResponseState {
	object Success : ResponseState()
	object Error : ResponseState()
	object Loading : ResponseState()
}