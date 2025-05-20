package com.app.capitalone_pokeapi_app.data.remote

import com.app.capitalone_pokeapi_app.domain.model.PokemonDetailResponse
import com.app.capitalone_pokeapi_app.domain.model.PokemonListResponse

interface PokeApiService {
    suspend fun fetchPokemonList(): PokemonListResponse
    suspend fun fetchPokemonDetail(id: String): PokemonDetailResponse
}