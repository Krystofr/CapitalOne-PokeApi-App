package com.app.capitalone_pokeapi_app.navigation

sealed class NavRoutes(val route: String) {

    data object PokemonListScreen : NavRoutes("pokemon_list_screen")

    data object PokemonDetailScreen : NavRoutes("pokemon_detail_screen/{pokemonId}") {
        fun createRoute(id: Int) = "pokemon_detail_screen/$id"
    }
}