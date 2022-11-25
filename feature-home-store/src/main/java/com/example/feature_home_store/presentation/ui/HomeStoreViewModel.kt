package com.example.feature_home_store.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.utils.ResourceState
import com.example.base.utils.ResponseNetwork
import com.example.feature_home_store.domain.model.HomeStoreList


import com.example.feature_home_store.domain.use_cases.GetHomeStoreUseCase
import com.example.feature_home_store.domain.use_cases.GetNumberItemsCartUseCase

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


internal class HomeStoreViewModel(
	private val getHomeStoreUseCase: GetHomeStoreUseCase,
	private val getNumberItemsCartUseCase: GetNumberItemsCartUseCase,
) : ViewModel() {
	private val _homeStoreStateFlow = MutableStateFlow(ResourceState<HomeStoreList>())
	val homeStoreStateFlow = _homeStoreStateFlow.asStateFlow()

	private val _shoppingCartSizeStateFlow = MutableStateFlow(ResourceState<Int>(success = 0))

	val shoppingCartSizeStateFlow = _shoppingCartSizeStateFlow.asStateFlow()

	init {
		getHomeStore()
		getNumberItemCart()
	}

	private fun getHomeStore() {
		getHomeStoreUseCase().onEach { result ->
			when (result) {
				is ResponseNetwork.Success -> {
					_homeStoreStateFlow.value = ResourceState(success = result.data)
				}
				is ResponseNetwork.Error -> {
					_homeStoreStateFlow.value = ResourceState(error = result.message
						?: "An unexpected error occurred while getting all products")
				}
				is ResponseNetwork.Loading -> {
					_homeStoreStateFlow.value = ResourceState(loading = true)
				}
			}
		}.launchIn(viewModelScope)
	}


	private fun getNumberItemCart() {
		getNumberItemsCartUseCase().onEach { result ->
			when (result) {
				is ResponseNetwork.Success -> {
					_shoppingCartSizeStateFlow.value =
						ResourceState<Int>(success = result.data ?: 0)
				}
				is ResponseNetwork.Error -> {
					_shoppingCartSizeStateFlow.value = ResourceState<Int>(error = result.message
						?: "An unexpected error occurred while loading basket size")
				}
				is ResponseNetwork.Loading -> {
					_shoppingCartSizeStateFlow.value = ResourceState<Int>(loading = true)
				}
			}
		}.launchIn(viewModelScope)
	}
}