package com.mk8.domain.usecase.character

import com.mk8.data.repsonse.CharactersResponse
import com.mk8.domain.provider.CharacterProvider
import com.mk8.domain.usecase.base.UseCaseWithParams

class RemoveFavouriteCharacterUseCase(
    private val characterProvider: CharacterProvider
) : UseCaseWithParams<Unit, CharactersResponse> {

    override suspend fun execute(params: CharactersResponse) = characterProvider.removeCharacterFavourite(params)
}