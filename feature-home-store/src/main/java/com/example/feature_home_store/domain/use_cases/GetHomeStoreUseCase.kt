package com.example.feature_home_store.domain.use_cases

import com.example.base.utils.ResponseNetwork
import com.bumptech.glide.load.engine.Resource
import com.example.feature_home_store.data.model.home_store.toHomeStoreListDto
import com.example.feature_home_store.domain.model.HomeStoreList
import com.example.feature_home_store.domain.repository.HomeStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class GetHomeStoreUseCase(private val repository: HomeStoreRepository) {
	operator fun invoke(): Flow<ResponseNetwork<HomeStoreList>> = flow {
		try {
			emit(ResponseNetwork.Loading())

			val items = repository.getHomeStoreList().toHomeStoreListDto()
			emit(ResponseNetwork.Success(items))
		} catch (e: HttpException) {
			emit(ResponseNetwork.Error(e.localizedMessage ?: "An unexpected error occurred"))
		} catch (e: IOException) {
			emit(ResponseNetwork.Error("Couldn't reach server. Check your internet connection."))
		}
	}
}