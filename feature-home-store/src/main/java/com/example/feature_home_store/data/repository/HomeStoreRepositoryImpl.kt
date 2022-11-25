package com.example.feature_home_store.data.repository

import com.example.feature_home_store.data.model.home_store.HomeStoreListDto
import com.example.feature_home_store.data.model.shopping_cart.ShoppingCartDto
import com.example.feature_home_store.data.network.HomeStoreApi
import com.example.feature_home_store.domain.repository.HomeStoreRepository

internal class HomeStoreRepositoryImpl(private val repository: HomeStoreApi) : HomeStoreRepository {

	override suspend fun getHomeStoreList(): HomeStoreListDto = repository.getHomeStoreData()

	override suspend fun getNumberItemsShoppingCart(): ShoppingCartDto =
		repository.getNumberItemsCart()
}