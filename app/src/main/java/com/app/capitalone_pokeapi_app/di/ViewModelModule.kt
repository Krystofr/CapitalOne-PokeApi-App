package com.app.capitalone_pokeapi_app.di

import com.app.capitalone_pokeapi_app.presentation.PokemonViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        PokemonViewModel(
            getPokemonListUseCase = get(),
            getPokemonDetailUseCase = get()
        )
    }
}