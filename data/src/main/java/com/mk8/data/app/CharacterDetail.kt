package com.mk8.data.app

import com.mk8.data.repsonse.LocationResponse

data class CharacterDetail(
    val id: Int? = 0,
    val name: String? = "",
    val image: String? = "",
    val locationResponse: LocationResponse,
    val isFavourite: Boolean
)