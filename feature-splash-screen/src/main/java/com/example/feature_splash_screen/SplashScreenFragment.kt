package com.example.feature_splash_screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.base.navigation.DeepLink
import com.example.base.navigation.navigationToDeepLink

class SplashScreenFragment : Fragment() {


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		return inflater.inflate(R.layout.fragment_splash_screen, container, false)
	}

	override fun onStart() {
		super.onStart()
		Handler(Looper.getMainLooper()).postDelayed({
			view?.let {
				if (it.isVisible) {
					navigationToDeepLink(DeepLink.homeStore,this)
				}
			}
		}, 2000)
	}
}