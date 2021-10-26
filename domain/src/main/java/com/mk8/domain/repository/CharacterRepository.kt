package com.mk8.domain.repository

import com.mk8.data.Either
import com.mk8.data.repsonse.CharactersResponse
import com.mk8.data.repsonse.DataResponse
import com.mk8.domain.ApiClient

class CharacterRepository(private val apiClient: ApiClient) {

    suspend fun getCharacterList(): Either<String, DataResponse> =
        apiClient.getCharacterList()

    suspend fun getCharacterByIdentifier(identifier: Int): Either<String, CharactersResponse> =
        apiClient.getCharacterByIdentifier(identifier)

    suspend fun getMoreCharacterList(url: String): Either<String, DataResponse> =
        apiClient.getMoreCharacterList(url)
}