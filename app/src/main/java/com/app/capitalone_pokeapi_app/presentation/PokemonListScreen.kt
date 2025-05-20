package com.app.capitalone_pokeapi_app.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.capitalone_pokeapi_app.R
import com.app.capitalone_pokeapi_app.domain.model.Pokemon
import com.app.capitalone_pokeapi_app.utils.Resource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    viewModel: PokemonViewModel = koinViewModel(),
    onNavigateToDetail: (Int) -> Unit
) {
    val state by viewModel.pokemonListState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        Log.d("PokemonScreen", "Calling ViewModel.loadPokemonList()")
        viewModel.loadPokemonList()
    }

    Scaffold(
        containerColor = White,
        topBar = {
            TopAppBar(
                title = {
                    Text("Pokemons", color = Black, fontSize = 17.sp)
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = White)
            )
        }
    ) { innerPadding ->
        when (state) {
            is Resource.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is Resource.Success -> {
                val pokemonList = (state as Resource.Success<List<Pokemon>>).data
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = innerPadding,
                    state = rememberLazyListState(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(pokemonList) { pokemon ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            PokemonItem(
                                pokemon = pokemon,
                                onNavigateToDetail = {
                                    onNavigateToDetail(pokemon.id)
                                }
                            )
                        }
                    }
                }
            }

            is Resource.Error -> Text("Failed to load Pokemons")
        }

    }

}


@Composable
fun PokemonItem(pokemon: Pokemon, onNavigateToDetail: (Int) -> Unit) {

    val imageUrl = ImageRequest.Builder(LocalContext.current)
        .data(pokemon.imageUrl)
        .crossfade(true)
        .error(R.drawable.ic_launcher_foreground)
        .build()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp, pressedElevation = 6.dp),
        onClick = {
            onNavigateToDetail(pokemon.id)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(Modifier.wrapContentSize(), contentAlignment = Alignment.Center) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = pokemon.name,
                    modifier = Modifier
                        .size(150.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                pokemon.name,
                fontWeight = FontWeight.Bold,
                color = Black,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
