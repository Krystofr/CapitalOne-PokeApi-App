package com.app.capitalone_pokeapi_app.data.remote

import com.app.capitalone_pokeapi_app.domain.model.PokemonDetailResponse
import com.app.capitalone_pokeapi_app.domain.model.PokemonListResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class PokeApiServiceImpl(
    private val client: HttpClient
) : PokeApiService {

    override suspend fun fetchPokemonList(): PokemonListResponse {
        return client.get("pokemon")
    }

    override suspend fun fetchPokemonDetail(id: String): PokemonDetailResponse {
        return client.get("pokemon/$id")
    }
}
