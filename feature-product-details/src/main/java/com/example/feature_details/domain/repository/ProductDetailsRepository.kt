package com.example.feature_details.domain.repository

import com.example.feature_details.data.model.ProductDetailsDto

interface ProductDetailsRepository {
	suspend fun getProductDetails(): ProductDetailsDto
}