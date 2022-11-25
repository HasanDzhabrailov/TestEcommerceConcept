package com.example.feature_home_store.data.model.home_store

import com.example.feature_home_store.domain.model.HomeStoreList

data class HomeStoreListDto(
	val best_seller: List<BestSellerDto>,
	val home_store: List<HotSellerDto>,
)

fun HomeStoreListDto.toHomeStoreListDto(): HomeStoreList = HomeStoreList(
	bestSeller = best_seller.map { it.toBestSellerDto() },
	hotSeller = home_store.map { it.toHotSeller() }
)