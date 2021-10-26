package com.mk8.pruebarickmorty.config.koin

import com.mk8.pruebarickmorty.screen.detail.DetailViewModel
import com.mk8.pruebarickmorty.screen.favourite.FavouriteViewModel
import com.mk8.pruebarickmorty.screen.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewModel(get(), get()) }

    viewModel { DetailViewModel(get(), get(), get(), get()) }

    viewModel { FavouriteViewModel(get()) }
}