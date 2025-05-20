package com.app.capitalone_pokeapi_app.domain.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val imageUrl: String
)

fun PokemonDto.toDomain(): Pokemon {
    val id = url.trimEnd('/').split("/").last().toInt()
    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    return Pokemon(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        imageUrl = imageUrl
    )
}

fun PokemonDetailResponse.toDomain(): PokemonDetail {
    return PokemonDetail(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        height = height,
        imageUrl = sprites.frontDefault
    )
}

