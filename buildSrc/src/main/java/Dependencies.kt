object Versions {
	//Design
	const val appcompat = "1.5.1"
	const val material = "1.7.0"
	const val constraintLayout = "2.1.4"

	//Glide
	const val glide = "4.12.0"
	const val glideCompiler = "4.12.0"
	//Koin
	const val koin = "3.2.0"
	//const val koinPlugin = "3.2.0"

	//Kotlin
	const val core = "1.7.0"
	const val coroutinesCore = "1.6.0"
	const val coroutinesAndroid = "1.6.0"

	//LifecycleExtensions
	const val lifecycleExtensions = "2.2.0"

	//LifecycleLiveData
	const val LifecycleLiveData = "2.5.1"

	//Navigation
	const val navigation = "2.5.3"



	//Retrofit
	const val retrofit = "2.9.0"
	const val gson = "2.9.0"

	//Test
	const val jUnit = "4.13.2"
	const val ext = "1.1.4"
	const val espressoCore = "3.5.0"

	// ViewBinding
	const val viewBinding = "4.0.1"

	const val fragmentKtx =  "1.5.2"

}

object Config{
	const val compileSdk = 32
	const val minSdk = 24
	const val targetSdk = 32
}


object Design {
	const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
	const val material = "com.google.android.material:material:${Versions.material}"
	const val constraintLayout =
		"androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}

object Glide {
	const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
	const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideCompiler}"
}
object Koin {
	const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
	const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
	const val koinPlugin = "io.insert-koin:koin-gradle-plugin:${Versions.koin}"
}
object Kotlin {
	const val core = "androidx.core:core-ktx:${Versions.core}"
	const val coroutinesCore =
		"org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
	const val coroutinesAndroid =
		"org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}

object Lifecycle {
	const val lifecycleExtensions =
		"androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
	const val lifecycleViewModel =
		"androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LifecycleLiveData}"
	const val lifecycleLiveData =
		"androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LifecycleLiveData}"
	const val lifecycleCommon =
		"androidx.lifecycle:lifecycle-common-java8:${Versions.LifecycleLiveData}"
	const val lifecycleCompiler = "ndroidx.lifecycle:lifecycle-compiler:${Versions.LifecycleLiveData}"
}

object Modules {
	const val base = ":base"
	const val homeStore = ":feature-home-store"
	const val productDetails = ":feature-product-details"
	const val shoppingCart = ":feature-shopping-cart"
	const val splashScreen = ":feature-splash-screen"
	const val myCart = ":feature-my-cart"
	const val navigation = ":navigation"
}

object Navigation {
	const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
	const val navUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
	const val sageArgsNav = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object Retrofit {
	const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
	const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
	const val gson = "com.google.code.gson:gson:${Versions.gson}"
}

object Tests {
	const val junit = "junit:junit:${Versions.jUnit}"
	const val ext = "androidx.test.ext:junit:${Versions.ext}"
	const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object FragmentKtxLibrary {
	const val fragmentKtxLibrary =  "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
}
object ViewBindingLibrary {
	const val viewBinding =
		"com.android.databinding:viewbinding:${Versions.viewBinding}"
}

