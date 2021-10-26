package com.mk8.pruebarickmorty.config.network

import com.mk8.data.repsonse.CharactersResponse
import com.mk8.data.repsonse.DataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface RickMortyService {

    @GET("character")
    suspend fun getCharacterList(): Response<DataResponse>

    @GET
    suspend fun getMoreCharacterList(@Url url: String): Response<DataResponse>

    @GET("character/{id}")
    suspend fun getCharacterByIdentifier(@Path("id") identifier: Int): Response<CharactersResponse>
}