package com.example.base.utils

data class ResourceState<DataSource>(
	val loading: Boolean = false,
	val success: DataSource? =null,
	val error: String = "",
)

