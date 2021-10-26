package com.mk8.pruebarickmorty.config.koin

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mk8.pruebarickmorty.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object InjectorModule {

    fun provideModules() = listOf(domainModule, networkModule, useCaseModule, viewModelModule)

    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(provideOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    private fun provideOkHttpClient() =
        OkHttpClient().newBuilder()
            .addInterceptor { chain ->
                val newRequestBuilder = chain.request().newBuilder()
                newRequestBuilder.addHeader("platform", "android")
                newRequestBuilder.addHeader("lang", "ES")
                chain.proceed(newRequestBuilder.build())
            }
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .build()
}