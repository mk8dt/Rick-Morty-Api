package com.mk8.pruebarickmorty.config.koin

import com.mk8.domain.cache.CharacterFavouritesCache
import com.mk8.domain.cache.NextPageCache
import com.mk8.domain.provider.CharacterProvider
import com.mk8.domain.repository.CharacterRepository
import org.koin.dsl.module

val domainModule = module {

    //CHARACTERS
    single { CharacterProvider(get(),get(),get()) }
    single { CharacterRepository(get()) }
    single { CharacterFavouritesCache() }
    single { NextPageCache() }
}