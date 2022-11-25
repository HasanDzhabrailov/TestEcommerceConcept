package com.example.feature_details.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.utils.ResourceState
import com.example.base.utils.ResponseNetwork
import com.example.feature_details.domain.model.ProductDetails
import com.example.feature_details.domain.use_cases.GetProductDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProductDetailsViewModel(private val getProductDetailsUseCase: GetProductDetailsUseCase) :
	ViewModel() {
	private val _productDetailsStateFlow = MutableStateFlow(ResourceState<ProductDetails>())
	val productDetailsStateFlow = _productDetailsStateFlow.asStateFlow()

	init {
		getProductDetails()
	}

	private fun getProductDetails() {
		getProductDetailsUseCase().onEach { result ->
			when (result) {
				is ResponseNetwork.Success -> {
					_productDetailsStateFlow.value = ResourceState(success = result.data)
				}
				is ResponseNetwork.Error -> {
					_productDetailsStateFlow.value = ResourceState(
						error = result.message ?: "An unexpected error occurred"
					)
				}
				is ResponseNetwork.Loading -> {
					_productDetailsStateFlow.value = ResourceState(loading = true)
				}
			}
		}.launchIn(viewModelScope)
	}

}