package com.app.capitalone_pokeapi_app.data.repository

import com.app.capitalone_pokeapi_app.domain.model.Pokemon
import com.app.capitalone_pokeapi_app.domain.model.PokemonDetail
import com.app.capitalone_pokeapi_app.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<Resource<List<Pokemon>>>
    fun getPokemonDetail(id: String): Flow<Resource<PokemonDetail>>
}
