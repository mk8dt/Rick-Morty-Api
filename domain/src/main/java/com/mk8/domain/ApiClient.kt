package com.mk8.domain

import com.mk8.data.Either
import com.mk8.data.repsonse.CharactersResponse
import com.mk8.data.repsonse.DataResponse

interface ApiClient {

    suspend fun getCharacterList(): Either<String, DataResponse>

    suspend fun getMoreCharacterList(url: String): Either<String, DataResponse>

    suspend fun getCharacterByIdentifier(identifier: Int): Either<String, CharactersResponse>
}