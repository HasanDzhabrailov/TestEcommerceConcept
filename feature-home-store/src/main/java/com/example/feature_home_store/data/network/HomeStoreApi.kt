package com.example.feature_home_store.data.network

import com.example.feature_home_store.data.model.home_store.HomeStoreListDto
import com.example.feature_home_store.data.model.shopping_cart.ShoppingCartDto
import retrofit2.http.GET

interface HomeStoreApi {
	@GET("654bd15e-b121-49ba-a588-960956b15175")
	suspend fun getHomeStoreData(): HomeStoreListDto

	@GET("53539a72-3c5f-4f30-bbb1-6ca10d42c149")
	suspend fun getNumberItemsCart(): ShoppingCartDto
}