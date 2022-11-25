package com.example.feature_home_store.presentation.ui

import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.lifecycle.lifecycleScope
import com.example.base.navigation.DeepLink
import com.example.base.navigation.navigationToDeepLink
import com.example.feature_home_store.databinding.FragmentHomeStoreBinding
import com.example.feature_home_store.presentation.adapters.BestSellerAdapter
import com.example.feature_home_store.presentation.adapters.HotSellerVPAdapter
import com.example.base.utils.*
import com.example.feature_home_store.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeStoreFragment :
	BaseFragment<FragmentHomeStoreBinding>(FragmentHomeStoreBinding::inflate) {
	private lateinit var dialog: BottomSheetDialog
	private val homeStoreViewModel by viewModel<HomeStoreViewModel>()

	override fun onCreateView() {
		activity?.window?.setLightSystemBars(requireContext())

		lifecycleScope.launch {
			homeStoreViewModel.homeStoreStateFlow.collectLatest {
				binding.hotSalesProgressBar.showProgressBarWhenLoading(it.loading)
				binding.bestSellerProgressBar.showProgressBarWhenLoading(it.loading)
				it.success?.let { items ->
					val hotSalesViewPagerAdapter = HotSellerVPAdapter(
						hotSales = items.hotSeller,
						context = requireContext()
					)
					binding.hotSalesViewPager.adapter = hotSalesViewPagerAdapter

					binding.bestSellerRecyclerView.apply {
						layoutManager = GridLayoutManager(requireContext(), 2)
						adapter = BestSellerAdapter(
							items.bestSeller, requireContext(), this@HomeStoreFragment)
					}
				}
			}
		}
		lifecycleScope.launch {
			homeStoreViewModel.shoppingCartSizeStateFlow.collectLatest {

				if (it.loading)
					binding.cardItemCountCardView.visibility = View.INVISIBLE
				else binding.cardItemCountCardView.visibility = View.VISIBLE
				when (it.success != null) {
					true -> {
						if (it.success!! >= 2) {
							binding.cardItemCountCardView.visibility = View.VISIBLE
							binding.cartItemCountTextView.text = it.success.toString()
						} else binding.cardItemCountCardView.visibility = View.INVISIBLE
					}
					else -> {}
				}
			}
		}

	}


	override fun setupClickListener() = with(binding) {
		filterIcon.setOnClickListener { showFilterBottomSheet() }
		cardView.setOnClickListener {}
		bagIcon.setOnClickListener {
			navigationToDeepLink(DeepLink.shoppingCart, this@HomeStoreFragment)
		}
		toggleButtonGroup.selectButton(R.id.phoneButton)
		toggleButtonGroup.setOnSelectListener { button ->
			when (button.selectedText) {
				"phoneButton" -> {
					phoneTV.toggleTextColor(
						requireContext(), phoneTV, computerTV, healthTV, booksTV
					)
				}
				"computerButton" -> {
					computerTV.toggleTextColor(
						requireContext(), phoneTV, computerTV, healthTV, booksTV
					)
				}
				"healthButton" -> {
					healthTV.toggleTextColor(
						requireContext(), phoneTV, computerTV, healthTV, booksTV
					)
				}
				"booksButton" -> {
					booksTV.toggleTextColor(
						requireContext(), phoneTV, computerTV, healthTV, booksTV
					)
				}
			}
		}
	}

	private fun showFilterBottomSheet() {
		dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
		dialog.apply {
			setContentView(R.layout.filter_options_bottom_sheet)

			this.findViewById<CardView>(R.id.closeCardView)?.setOnClickListener { hide() }
			this.findViewById<Button>(R.id.doneButton)?.setOnClickListener { hide() }

			findViewById<AutoCompleteTextView>(
				R.id.brandAutoCompleteTextView
			)?.setDropDownMenu(R.array.brands)

			findViewById<AutoCompleteTextView>(
				R.id.priceAutoCompleteTextView
			)?.setDropDownMenu(R.array.price)

			findViewById<AutoCompleteTextView>(
				R.id.sizeCompleteTextView
			)?.setDropDownMenu(R.array.size)

			show()
		}
	}

	private fun AutoCompleteTextView.setDropDownMenu(stringArray: Int) {
		val brands = resources.getStringArray(stringArray)
		val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, brands)
		this.setAdapter(arrayAdapter)
	}
}