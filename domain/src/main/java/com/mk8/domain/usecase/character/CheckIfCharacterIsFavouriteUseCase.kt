package com.mk8.domain.usecase.character

import com.mk8.data.Either
import com.mk8.data.repsonse.CharactersResponse
import com.mk8.domain.provider.CharacterProvider
import com.mk8.domain.usecase.base.UseCaseWithParams

class CheckIfCharacterIsFavouriteUseCase(
    private val characterProvider: CharacterProvider
) : UseCaseWithParams<Either<String,Boolean>,CharactersResponse>{

    override suspend fun execute(params: CharactersResponse): Either<String, Boolean> =
        characterProvider.checkIfCharacterIsFavourite(params)
}