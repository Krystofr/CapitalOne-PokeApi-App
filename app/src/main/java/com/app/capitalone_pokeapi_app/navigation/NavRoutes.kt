package com.app.capitalone_pokeapi_app.navigation

sealed class NavRoutes(val route: String) {

    object PokemonListScreen : NavRoutes("pokemon_list_screen")

    object PokemonDetailScreen : NavRoutes("pokemon_detail_screen")
}