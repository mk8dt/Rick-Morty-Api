package com.mk8.data.repsonse

data class CharactersResponse(
    val id: Int,
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val image: String,
    val location: LocationResponse,
    var isFavourite: Boolean = false
)