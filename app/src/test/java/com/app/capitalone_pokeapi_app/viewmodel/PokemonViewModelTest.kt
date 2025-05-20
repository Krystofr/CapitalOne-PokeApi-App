package com.app.capitalone_pokeapi_app.viewmodel

import app.cash.turbine.test
import com.app.capitalone_pokeapi_app.domain.model.Pokemon
import com.app.capitalone_pokeapi_app.domain.usecase.GetPokemonDetailUseCase
import com.app.capitalone_pokeapi_app.domain.usecase.GetPokemonListUseCase
import com.app.capitalone_pokeapi_app.presentation.PokemonViewModel
import com.app.capitalone_pokeapi_app.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonViewModelTest {

    private val getPokemonListUseCase: GetPokemonListUseCase = mockk()
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase = mockk()
    private lateinit var viewModel: PokemonViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = PokemonViewModel(getPokemonListUseCase, getPokemonDetailUseCase)
    }

    @Test
    fun `loadPokemonList emits loading then success`() = runTest {
        val expectedList = listOf(Pokemon(1, "Bulbasaur", "img_url"))

        coEvery { getPokemonListUseCase() } returns flow {
            emit(Resource.Loading)
            emit(Resource.Success(expectedList))
        }

        viewModel.loadPokemonList()

        viewModel.pokemonListState.test {
            assert(awaitItem() is Resource.Loading)
            val item = awaitItem()
            assert(item is Resource.Success && item.data == expectedList)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
