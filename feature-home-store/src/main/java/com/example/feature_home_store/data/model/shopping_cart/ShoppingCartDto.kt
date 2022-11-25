package com.example.feature_home_store.data.model.shopping_cart

data class ShoppingCartDto(
	val basket: List<ShoppingCartInfoDto>,
	val delivery: String,
	val id: String,
	val total: Int,
)
