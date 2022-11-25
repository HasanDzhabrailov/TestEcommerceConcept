package com.example.feature_home_store.data.model.home_store

import com.example.feature_home_store.domain.model.HotSeller

data class HotSellerDto(
	val id: Int,
	val is_buy: Boolean,
	val is_new: Boolean,
	val picture: String,
	val subtitle: String,
	val title: String,
)

fun HotSellerDto.toHotSeller(): HotSeller = HotSeller(
	id = id,
	isBuy = is_buy,
	isNew = is_new,
	picture = picture,
	subtitle = subtitle,
	title = title
)