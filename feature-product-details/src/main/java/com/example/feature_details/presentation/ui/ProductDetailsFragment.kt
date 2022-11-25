package com.example.feature_details.presentation.ui

import android.annotation.SuppressLint
import android.view.View
import androidx.core.graphics.toColorInt


import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLink
import com.example.base.R
import com.example.base.utils.*

import androidx.navigation.fragment.findNavController
import com.example.base.navigation.DeepLink
import com.example.base.navigation.navigationToDeepLink
import com.example.feature_details.databinding.FragmentProductDetailsBinding
import com.example.feature_details.domain.model.ProductDetails
import com.example.feature_details.presentation.adapter.ProductDetailsViewPagerAdapter
import com.example.feature_details.presentation.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductDetailsFragment : BaseFragment<FragmentProductDetailsBinding>(
	FragmentProductDetailsBinding::inflate
){
	private val viewModel by viewModel<ProductDetailsViewModel>()


	@SuppressLint("SetTextI18n")
	override fun onCreateView() {

		lifecycleScope.launch {
			viewModel.productDetailsStateFlow.collectLatest {
				binding.productDetailsProgressBar.showProgressBarWhenLoading(it.loading)
				if (it.loading) {
					binding.ratingBar.visibility = View.INVISIBLE
					binding.detailsLinearLayout.visibility = View.INVISIBLE
				} else {
					binding.ratingBar.visibility = View.VISIBLE
					binding.detailsLinearLayout.visibility = View.VISIBLE
				}

				it.success?.let { item ->
					val productDetailsViewPagerAdapter = ProductDetailsViewPagerAdapter(
						images = item.images, context = requireContext()
					)

					binding.phonePreviewViewPager.adapter = productDetailsViewPagerAdapter
					binding.brandTextView.text = item.title

					if (item.isFavorites) binding.heartIcon.setImageDrawable(
						requireContext(), R.drawable.ic_heart_filled
					)
					else binding.heartIcon.setImageDrawable(
						requireContext(),R.drawable.ic_heart_outline
					)

					setViewPagerAdapter(item)

					binding.firstColorCardView.setCardBackgroundColor(item.color[0].toColorInt())
					binding.secondColorCardView.setCardBackgroundColor(item.color[1].toColorInt())

					binding.firstCapacityTextView.text = "${item.capacity[0]} gb"
					binding.secondCapacityTextView.text = "${item.capacity[1]} gb"

					binding.priceTextView.text = "$${item.price}.00"
				}
			}
		}
	}

	override fun setupClickListener() = with(binding) {
		backCardView.setOnClickListener { findNavController().popBackStack() }

		firstCapacityTextView.setOnClickListener {
			firstCapacityCardView.setCardBgColor(requireContext(), R.color.orange)
			secondCapacityCardView.setCardBgColor(requireContext(), R.color.white)
			firstCapacityTextView.setTextColor(requireContext(), R.color.white)
			secondCapacityTextView.setTextColor(requireContext(), R.color.text_gray)
			secondCapacityCardView.cardElevation = 0f
		}

		secondCapacityTextView.setOnClickListener {
			secondCapacityCardView.setCardBgColor(requireContext(), R.color.orange)
			firstCapacityCardView.setCardBgColor(requireContext(), R.color.white)
			secondCapacityTextView.setTextColor(requireContext(), R.color.white)
			firstCapacityTextView.setTextColor(requireContext(), R.color.text_gray)
			firstCapacityCardView.cardElevation = 0f
		}

		firstColorCardView.setOnClickListener {
			secondColorCheckIcon.visibility = View.GONE
			firstColorCheckIcon.visibility = View.VISIBLE
		}

		secondColorCardView.setOnClickListener {
			firstColorCheckIcon.visibility = View.GONE
			secondColorCheckIcon.visibility = View.VISIBLE
		}

		basketCardView.setOnClickListener {
			navigationToDeepLink(DeepLink.shoppingCart,this@ProductDetailsFragment)
		}
	}

	private fun setViewPagerAdapter(item: ProductDetails) {
		val adapter = ViewPagerAdapter(item)
		binding.viewPager.adapter = adapter

		TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
			when (position) {
				0 -> tab.text =
					requireContext().getText(com.example.feature_details.R.string.shop)
				1 -> tab.text =
					requireContext().getText(com.example.feature_details.R.string.details)
				2 -> tab.text =
					requireContext().getText(com.example.feature_details.R.string.features)
			}
		}.attach()

		binding.tabLayout.changeSelectedTabItemFontFamily(0, true)

		binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
			override fun onTabSelected(tab: TabLayout.Tab) {
				binding.tabLayout.changeSelectedTabItemFontFamily(tab.position, true)
			}

			override fun onTabUnselected(tab: TabLayout.Tab) {
				binding.tabLayout.changeSelectedTabItemFontFamily(tab.position, false)
			}

			override fun onTabReselected(tab: TabLayout.Tab) {}
		})
	}

}