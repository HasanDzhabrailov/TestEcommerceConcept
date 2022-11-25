package com.example.ecommerceconcept.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
	single {
		HttpLoggingInterceptor().apply {
			level = HttpLoggingInterceptor.Level.BODY
		}
	}
	single {
		OkHttpClient.Builder().addInterceptor(get() as HttpLoggingInterceptor).build()
	}
	single {
		Retrofit.Builder()
			.client(get())
			.addConverterFactory(GsonConverterFactory.create())
			.baseUrl("https://run.mocky.io/v3/")
			.build()
	}
}