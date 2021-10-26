package com.mk8.domain.provider

import com.mk8.data.Either
import com.mk8.data.mapValue
import com.mk8.data.repsonse.CharactersResponse
import com.mk8.data.value
import com.mk8.domain.cache.CharacterFavouritesCache
import com.mk8.domain.cache.NextPageCache
import com.mk8.domain.repository.CharacterRepository

class CharacterProvider(
    private val characterRepository: CharacterRepository,
    private val nextPageCache: NextPageCache,
    private val characterFavouritesCache: CharacterFavouritesCache
) {

    suspend fun getCharacterList(): Either<String, List<CharactersResponse>> =
        characterRepository.getCharacterList() mapValue {
            nextPageCache.save(it.info.next)
            it.results
        }

    suspend fun getMoreCharacterList(): Either<String, List<CharactersResponse>> {
        val url = nextPageCache.load()
        return if (url == null) value(listOf())
        else characterRepository.getMoreCharacterList(url) mapValue {
            nextPageCache.save(it.info.next)
            it.results
        }
    }

    suspend fun getCharacterByIdentifier(identifier: Int): Either<String, CharactersResponse> =
        characterRepository.getCharacterByIdentifier(identifier)

    fun getFavouriteCharacter(): Either<String, List<CharactersResponse>> =
        value(characterFavouritesCache.load())

    fun addCharacterFavourite(charactersResponse: CharactersResponse) {
        characterFavouritesCache.addElement(charactersResponse)
    }

    fun removeCharacterFavourite(charactersResponse: CharactersResponse) {
        charactersResponse.isFavourite = true
        characterFavouritesCache.removeElement(charactersResponse)
    }

    fun checkIfCharacterIsFavourite(charactersResponse: CharactersResponse): Either<String, Boolean> {
        val checkFavourite = characterFavouritesCache.load().find { it.id == charactersResponse.id }
        return if (checkFavourite != null) value(true) else value(false)
    }
}