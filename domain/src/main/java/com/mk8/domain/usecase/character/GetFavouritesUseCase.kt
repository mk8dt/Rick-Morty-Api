package com.mk8.domain.usecase.character

import com.mk8.data.Either
import com.mk8.data.repsonse.CharactersResponse
import com.mk8.domain.provider.CharacterProvider
import com.mk8.domain.usecase.base.UseCase

class GetFavouritesUseCase(private val characterProvider: CharacterProvider) : UseCase<Either<String, List<CharactersResponse>>> {

    override suspend fun execute(): Either<String, List<CharactersResponse>> =
        characterProvider.getFavouriteCharacter()
}