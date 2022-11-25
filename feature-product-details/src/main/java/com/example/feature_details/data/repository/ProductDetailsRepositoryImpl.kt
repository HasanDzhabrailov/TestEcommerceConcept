package com.example.feature_details.data.repository

import com.example.feature_details.data.model.ProductDetailsDto
import com.example.feature_details.data.network.ProductDetailsApi
import com.example.feature_details.domain.repository.ProductDetailsRepository

class ProductDetailsRepositoryImpl(private val api:ProductDetailsApi):ProductDetailsRepository {
	override suspend fun getProductDetails(): ProductDetailsDto = api.getDetails()
}