package com.app.capitalone_pokeapi_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.capitalone_pokeapi_app.presentation.PokemonDetailsScreen
import com.app.capitalone_pokeapi_app.presentation.PokemonListScreen

@Composable
fun PokemonNavigation(navController: NavHostController) {

    NavHost(navController, startDestination = NavRoutes.PokemonListScreen.route) {
        composable(route = NavRoutes.PokemonListScreen.route) {
            PokemonListScreen(
                onNavigateToDetail = {
                    navController.navigate(NavRoutes.PokemonDetailScreen.createRoute(it))
                }
            )
        }
        composable(
            route = NavRoutes.PokemonDetailScreen.route,
            arguments = listOf(navArgument("pokemonId") { type = NavType.IntType })
        ) { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getInt("pokemonId") ?: return@composable
            PokemonDetailsScreen(
                pokemonId = pokemonId,
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}