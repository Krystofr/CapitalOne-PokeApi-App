package com.app.capitalone_pokeapi_app.domain.usecase

import com.app.capitalone_pokeapi_app.data.repository.PokemonRepository
import com.app.capitalone_pokeapi_app.domain.model.Pokemon
import com.app.capitalone_pokeapi_app.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCase(
    private val repository: PokemonRepository
) {
    operator fun invoke(): Flow<Resource<List<Pokemon>>> = repository.getPokemonList()

}