package com.example.feature_details.domain.use_cases



import com.example.base.utils.ResponseNetwork
import com.example.feature_details.data.model.toProductDetails
import com.example.feature_details.domain.model.ProductDetails
import com.example.feature_details.domain.repository.ProductDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetProductDetailsUseCase(private val repository: ProductDetailsRepository) {
	operator fun invoke(): Flow<ResponseNetwork<ProductDetails>> = flow {
		try {
			emit(ResponseNetwork.Loading())
			val item = repository.getProductDetails().toProductDetails()
			emit(ResponseNetwork.Success(item))
		} catch (e: HttpException) {
			emit(ResponseNetwork.Error(e.localizedMessage ?: "An unexpected error occurred"))
		} catch (e: IOException) {
			emit(ResponseNetwork.Error("Couldn't reach server. Check your internet connection."))
		}
	}
}