package com.app.capitalone_pokeapi_app.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.capitalone_pokeapi_app.presentation.PokemonDetailsScreen
import com.app.capitalone_pokeapi_app.presentation.PokemonListScreen
import com.app.capitalone_pokeapi_app.presentation.PokemonViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PokemonNavigation() {

    val pokemonViewModel = koinViewModel<PokemonViewModel>()

    SharedTransitionLayout {
        val navController = rememberNavController()
        NavHost(navController, startDestination = NavRoutes.PokemonListScreen.route) {
            composable(route = NavRoutes.PokemonListScreen.route) {
                PokemonListScreen(
                    pokemonViewModel,
                    onNavigateToDetail = {
                        navController.navigate(NavRoutes.PokemonDetailScreen.createRoute(it))
                    },
                    animatedVisibilityScope = this
                )
            }
            composable(
                route = NavRoutes.PokemonDetailScreen.route,
                arguments = listOf(navArgument("pokemonId") { type = NavType.IntType })
            ) { backStackEntry ->
                val pokemonId = backStackEntry.arguments?.getInt("pokemonId") ?: return@composable
                PokemonDetailsScreen(
                    pokemonId = pokemonId,
                    pokemonViewModel,
                    onBack = {
                        navController.navigateUp()
                    },
                    animatedVisibilityScope = this
                )
            }
        }
    }
}