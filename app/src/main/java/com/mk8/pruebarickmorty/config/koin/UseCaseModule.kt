package com.mk8.pruebarickmorty.config.koin

import com.mk8.domain.usecase.character.*
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetCharacterListUseCase(get()) }
    factory { GetMoreCharacterUseCase(get()) }
    factory { GetCharacterByIdentifierUseCase(get()) }

    factory { GetFavouritesUseCase(get()) }
    factory { AddFavouriteCharacterUseCase(get()) }
    factory { RemoveFavouriteCharacterUseCase(get()) }
    factory { CheckIfCharacterIsFavouriteUseCase(get()) }
}