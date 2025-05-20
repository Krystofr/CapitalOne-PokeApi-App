package com.app.capitalone_pokeapi_app.data.repository

import com.app.capitalone_pokeapi_app.data.remote.PokeApiService
import com.app.capitalone_pokeapi_app.domain.model.Pokemon
import com.app.capitalone_pokeapi_app.domain.model.PokemonDetail
import com.app.capitalone_pokeapi_app.domain.model.toDomain
import com.app.capitalone_pokeapi_app.utils.Resource
import com.app.capitalone_pokeapi_app.utils.safeApiCall
import kotlinx.coroutines.flow.Flow

class PokemonRepositoryImpl(
    private val api: PokeApiService
) : PokemonRepository {

    override fun getPokemonList(): Flow<Resource<List<Pokemon>>> =
        safeApiCall {
            api.fetchPokemonList().results.map { it.toDomain() }
        }

    override fun getPokemonDetail(id: String): Flow<Resource<PokemonDetail>> =
        safeApiCall {
            api.fetchPokemonDetail(id).toDomain()
        }
}
