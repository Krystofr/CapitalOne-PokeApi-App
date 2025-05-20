package com.app.capitalone_pokeapi_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.capitalone_pokeapi_app.presentation.PokemonDetailsScreen
import com.app.capitalone_pokeapi_app.presentation.PokemonListScreen

@Composable
fun PokemonNavigation(navController: NavHostController) {

    NavHost(navController, startDestination = NavRoutes.PokemonListScreen.route) {
        composable(route = NavRoutes.PokemonListScreen.route) {
            PokemonListScreen()
        }
        composable(route = NavRoutes.PokemonListScreen.route) {
            PokemonDetailsScreen()
        }
    }
}