package com.example.feature_home_store.domain.use_cases


import com.example.base.utils.ResponseNetwork

import com.example.feature_home_store.domain.repository.HomeStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetNumberItemsCartUseCase(private val repository: HomeStoreRepository) {
	operator fun invoke(): Flow<ResponseNetwork<Int>> = flow {
		try {
			emit(ResponseNetwork.Loading())
			val items = repository.getNumberItemsShoppingCart().basket.size
			emit(ResponseNetwork.Success(items))
		} catch (e: HttpException) {
			emit(ResponseNetwork.Error(e.localizedMessage ?: "An unexpected error occurred"))
		} catch (e: IOException) {
			emit(ResponseNetwork.Error("Couldn't reach server. Check your internet connection."))
		}
	}
}