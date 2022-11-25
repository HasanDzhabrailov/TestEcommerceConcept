package com.example.feature_details.di

import com.example.feature_details.data.network.ProductDetailsApi
import com.example.feature_details.data.repository.ProductDetailsRepositoryImpl
import com.example.feature_details.domain.repository.ProductDetailsRepository
import com.example.feature_details.domain.use_cases.GetProductDetailsUseCase
import com.example.feature_details.presentation.ui.ProductDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module
import retrofit2.Retrofit

val productDetailsModule = module{
	single { provideProductDetailsService(retrofit = get()) }

	single <ProductDetailsRepository>{ ProductDetailsRepositoryImpl(api = get()) }
	factory { GetProductDetailsUseCase(get()) }

	viewModel {
		ProductDetailsViewModel(get())
	}
}

 fun provideProductDetailsService(retrofit: Retrofit): ProductDetailsApi =
	retrofit.create(ProductDetailsApi::class.java)