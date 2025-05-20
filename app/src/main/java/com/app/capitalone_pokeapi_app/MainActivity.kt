package com.app.capitalone_pokeapi_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.app.capitalone_pokeapi_app.navigation.PokemonNavigation
import com.app.capitalone_pokeapi_app.ui.theme.CapitalOnePokeApiAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CapitalOnePokeApiAppTheme {
                PokemonNavigation()
            }
        }
    }
}