package com.app.capitalone_pokeapi_app.domain.usecase

import com.app.capitalone_pokeapi_app.data.repository.PokemonRepository
import com.app.capitalone_pokeapi_app.domain.model.PokemonDetail
import com.app.capitalone_pokeapi_app.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetPokemonDetailUseCase(
    private val repository: PokemonRepository
) {
    operator fun invoke(id: String): Flow<Resource<PokemonDetail>> {
        return repository.getPokemonDetail(id)
    }
}
