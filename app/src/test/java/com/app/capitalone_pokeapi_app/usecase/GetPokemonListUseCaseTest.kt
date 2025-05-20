package com.app.capitalone_pokeapi_app.usecase

import com.app.capitalone_pokeapi_app.data.repository.PokemonRepository
import com.app.capitalone_pokeapi_app.domain.model.Pokemon
import com.app.capitalone_pokeapi_app.domain.usecase.GetPokemonListUseCase
import com.app.capitalone_pokeapi_app.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetPokemonListUseCaseTest {

    private val repository: PokemonRepository = mockk()
    private val useCase = GetPokemonListUseCase(repository)

    @Test
    fun `invoke returns success flow from repository`() = runTest {
        val expected = listOf(Pokemon(1, "Bulbasaur", "img"))
        coEvery { repository.getPokemonList() } returns flow {
            emit(Resource.Success(expected))
        }

        val result = useCase().first()
        assert(result is Resource.Success && result.data == expected)
    }
}
