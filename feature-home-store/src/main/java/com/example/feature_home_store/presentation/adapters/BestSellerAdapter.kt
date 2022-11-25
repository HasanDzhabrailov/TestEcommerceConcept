package com.example.feature_home_store.presentation.adapters

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.base.navigation.DeepLink
import com.example.base.navigation.navigationToDeepLink
import com.example.base.utils.setImageDrawable

import com.example.feature_home_store.databinding.BestSellerItemBinding

import com.example.feature_home_store.domain.model.BestSeller
import com.example.feature_home_store.presentation.ui.HomeStoreFragment

internal class BestSellerAdapter(
	private val bestSeller: List<BestSeller>,
	private val context: Context,
	private val fragment: HomeStoreFragment,
) : RecyclerView.Adapter<BestSellerAdapter.MyViewHolder>() {
	class MyViewHolder(val binding: BestSellerItemBinding) :
		RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
		MyViewHolder(
			BestSellerItemBinding.inflate(
				LayoutInflater.from(parent.context), parent, false
			)
		)

	override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
		val bestSeller = bestSeller[position]
		holder.binding.apply {
			bestSellerContainer.setOnClickListener {
				navigationToDeepLink(DeepLink.productDetails, fragment)
			}

			newPriceTextView.text = bestSeller.discountPrice.toString()
			oldPriceTextView.text = bestSeller.priceWithoutDiscount.toString()
			oldPriceTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
			brandTextView.text = bestSeller.title

			Glide.with(context)
				.load(bestSeller.picture)
				.into(phonePreviewImageView)

			if (bestSeller.isFavorites) heartIcon.setImageDrawable(
				context, com.example.base.R.drawable.ic_heart_filled
			)
			else heartIcon.setImageDrawable(context, com.example.base.R.drawable.ic_heart_outline)
		}
	}

	override fun getItemCount(): Int = bestSeller.size
}
