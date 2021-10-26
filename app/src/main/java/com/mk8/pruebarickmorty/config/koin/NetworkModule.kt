package com.mk8.pruebarickmorty.config.koin

import com.mk8.domain.ApiClient
import com.mk8.pruebarickmorty.config.network.ApiClientImpl
import com.mk8.pruebarickmorty.config.network.NetworkController
import com.mk8.pruebarickmorty.config.network.RickMortyService
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {

    single<ApiClient> { ApiClientImpl(get(), get()) }

    single { InjectorModule.provideRetrofit() }

    single { get<Retrofit>().create(RickMortyService::class.java) }

    single { NetworkController() }
}