package com.example.ecommerceconcept.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
	single {
		Retrofit.Builder()
			.addConverterFactory(GsonConverterFactory.create())
			.baseUrl("https://run.mocky.io/")
			.build()
	}
}
