package com.example.feature_home_store.di

import com.example.feature_home_store.data.network.HomeStoreApi
import com.example.feature_home_store.data.repository.HomeStoreRepositoryImpl
import com.example.feature_home_store.domain.repository.HomeStoreRepository
import com.example.feature_home_store.domain.use_cases.GetHomeStoreUseCase
import com.example.feature_home_store.domain.use_cases.GetNumberItemsCartUseCase
import com.example.feature_home_store.presentation.ui.HomeStoreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val homeStoreModule = module {
	single { provideHomeStoreService(retrofit = get()) }

	single<HomeStoreRepository> { HomeStoreRepositoryImpl(repository = get()) }

	factory { GetHomeStoreUseCase(get()) }
	factory { GetNumberItemsCartUseCase(get()) }

	viewModel {
		HomeStoreViewModel(
			getHomeStoreUseCase = get(),
			getNumberItemsCartUseCase = get()
		)
	}
}

fun provideHomeStoreService(retrofit: Retrofit): HomeStoreApi =
	retrofit.create(HomeStoreApi::class.java)


