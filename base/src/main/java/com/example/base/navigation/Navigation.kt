package com.example.base.navigation

import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController

fun navigationToDeepLink(deepLink:String, fragment: Fragment) {
	val request = NavDeepLinkRequest.Builder
		.fromUri(deepLink.toUri())
		.build()
	findNavController(fragment).navigate(request)
}