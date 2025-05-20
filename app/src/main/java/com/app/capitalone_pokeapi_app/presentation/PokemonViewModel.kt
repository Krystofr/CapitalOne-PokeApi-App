package com.app.capitalone_pokeapi_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.capitalone_pokeapi_app.domain.model.Pokemon
import com.app.capitalone_pokeapi_app.domain.model.PokemonDetail
import com.app.capitalone_pokeapi_app.domain.usecase.GetPokemonDetailUseCase
import com.app.capitalone_pokeapi_app.domain.usecase.GetPokemonListUseCase
import com.app.capitalone_pokeapi_app.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {

    private val _pokemonListState = MutableStateFlow<Resource<List<Pokemon>>>(Resource.Loading)
    val pokemonListState = _pokemonListState.asStateFlow()

    private val _pokemonDetailState = MutableStateFlow<Resource<PokemonDetail>>(Resource.Loading)
    val pokemonDetailState = _pokemonDetailState.asStateFlow()

    fun loadPokemonList() {
        viewModelScope.launch {
            getPokemonListUseCase().onEach { result ->
                _pokemonListState.value = result
            }.catch { e ->
                _pokemonListState.value = Resource.Error(e.message ?: "An error occurred")
            }.launchIn(viewModelScope)
        }
    }

    fun loadPokemonDetail(id: String) {
        viewModelScope.launch {
            getPokemonDetailUseCase(id).onEach { result ->
                _pokemonDetailState.value = result
            }.catch { e ->
                _pokemonDetailState.value = Resource.Error(e.message ?: "An error occurred")
            }.launchIn(viewModelScope)
        }
    }

    fun resetDetailState() {
        _pokemonDetailState.value = Resource.Loading
    }
}
