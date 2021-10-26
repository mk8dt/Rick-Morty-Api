package com.mk8.pruebarickmorty.config.network

import com.mk8.data.Either
import com.mk8.data.repsonse.CharactersResponse
import com.mk8.data.repsonse.DataResponse
import com.mk8.domain.ApiClient

class ApiClientImpl(
    private val rickMortyService: RickMortyService,
    private val networkController: NetworkController
) : ApiClient {

    override suspend fun getCharacterList(): Either<String, DataResponse> =
        try {
            val response = rickMortyService.getCharacterList()
            networkController.checkResponse(response)
        } catch (e: Exception) {
            networkController.checkException(e)
        }

    override suspend fun getMoreCharacterList(url: String): Either<String, DataResponse> =
        try {
            val response = rickMortyService.getMoreCharacterList(url)
            networkController.checkResponse(response)
        } catch (e: Exception) {
            networkController.checkException(e)
        }

    override suspend fun getCharacterByIdentifier(identifier: Int): Either<String, CharactersResponse> =
        try {
            val response = rickMortyService.getCharacterByIdentifier(identifier)
            networkController.checkResponse(response)
        } catch (e: Exception) {
            networkController.checkException(e)
        }
}