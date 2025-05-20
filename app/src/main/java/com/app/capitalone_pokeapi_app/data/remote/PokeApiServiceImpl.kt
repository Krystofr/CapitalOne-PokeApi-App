package com.app.capitalone_pokeapi_app.data.remote

import android.util.Log
import com.app.capitalone_pokeapi_app.domain.model.PokemonDetailResponse
import com.app.capitalone_pokeapi_app.domain.model.PokemonListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PokeApiServiceImpl(
    private val client: HttpClient
) : PokeApiService {

    override suspend fun fetchPokemonList(): PokemonListResponse {
        Log.d("PokeApi", "Calling fetchPokemonList")
        return client.get("api/v2/pokemon").body()

    }

    override suspend fun fetchPokemonDetail(id: String): PokemonDetailResponse {
        Log.d("PokeApi", "Calling fetchPokemonDetail for $id")
        return client.get("api/v2/pokemon/$id").body()
    }
}
