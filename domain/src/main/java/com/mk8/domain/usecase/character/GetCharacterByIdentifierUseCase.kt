package com.mk8.domain.usecase.character

import com.mk8.data.Either
import com.mk8.data.repsonse.CharactersResponse
import com.mk8.domain.provider.CharacterProvider
import com.mk8.domain.usecase.base.UseCaseWithParams

class GetCharacterByIdentifierUseCase(
    private val characterProvider: CharacterProvider
) : UseCaseWithParams<Either<String, CharactersResponse>, Int> {

    override suspend fun execute(params: Int): Either<String, CharactersResponse> =
        characterProvider.getCharacterByIdentifier(params)
}