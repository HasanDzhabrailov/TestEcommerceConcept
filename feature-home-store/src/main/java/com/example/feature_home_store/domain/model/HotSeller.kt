package com.example.feature_home_store.domain.model

data class HotSeller(
	val id: Int,
	val isBuy: Boolean,
	val isNew: Boolean,
	val picture: String,
	val subtitle: String,
	val title: String,
)

