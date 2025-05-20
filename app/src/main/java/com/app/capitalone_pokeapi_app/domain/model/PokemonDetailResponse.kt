package com.app.capitalone_pokeapi_app.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val sprites: Sprites
)

@Serializable
data class Sprites(
    @SerialName("front_default")
    val frontDefault: String
)
