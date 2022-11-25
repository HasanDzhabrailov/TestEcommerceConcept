package com.example.ecommerceconcept

import android.app.Application
import com.example.ecommerceconcept.di.apiModule
import com.example.feature_details.di.productDetailsModule
import com.example.feature_home_store.di.homeStoreModule
import com.example.feature_shopping_cart.di.shoppingCartModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger(Level.DEBUG)
			androidContext(this@App)
			modules(
				listOf(	apiModule,
					homeStoreModule,
					productDetailsModule,
					shoppingCartModule)

			)
		}
	}
}