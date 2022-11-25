package com.example.feature_home_store.domain.repository

import com.example.feature_home_store.data.model.home_store.HomeStoreListDto
import com.example.feature_home_store.data.model.shopping_cart.ShoppingCartDto

interface HomeStoreRepository {
	suspend fun getHomeStoreList(): HomeStoreListDto
	suspend fun getNumberItemsShoppingCart(): ShoppingCartDto
}