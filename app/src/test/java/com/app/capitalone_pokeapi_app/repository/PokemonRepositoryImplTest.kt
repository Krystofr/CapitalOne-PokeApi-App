package com.app.capitalone_pokeapi_app.repository

import app.cash.turbine.test
import com.app.capitalone_pokeapi_app.data.remote.PokeApiService
import com.app.capitalone_pokeapi_app.data.repository.PokemonRepositoryImpl
import com.app.capitalone_pokeapi_app.domain.model.PokemonDto
import com.app.capitalone_pokeapi_app.domain.model.PokemonListResponse
import com.app.capitalone_pokeapi_app.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonRepositoryImplTest {

    private val api: PokeApiService = mockk()
    private val repository = PokemonRepositoryImpl(api)

    @Test
    fun `getPokemonList emits loading then success`() = runTest {
        val dto = PokemonListResponse(
            count = 1,
            results = listOf(PokemonDto("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"))
        )
        coEvery { api.fetchPokemonList() } returns dto

        repository.getPokemonList().test {
            assert(awaitItem() is Resource.Loading)
            val success = awaitItem()
            val data = (success as? Resource.Success)?.data
            assert(success is Resource.Success)
            assert(data?.first()?.name == "Bulbasaur")
            cancelAndIgnoreRemainingEvents()
        }
    }
}
