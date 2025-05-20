package com.app.capitalone_pokeapi_app.di

import com.app.capitalone_pokeapi_app.data.remote.PokeApiService
import com.app.capitalone_pokeapi_app.data.remote.PokeApiServiceImpl
import com.app.capitalone_pokeapi_app.data.repository.PokemonRepository
import com.app.capitalone_pokeapi_app.data.repository.PokemonRepositoryImpl
import com.app.capitalone_pokeapi_app.domain.usecase.GetPokemonDetailUseCase
import com.app.capitalone_pokeapi_app.domain.usecase.GetPokemonListUseCase
import com.app.capitalone_pokeapi_app.util.POKE_API_BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {

    single {
        HttpClient(engineFactory = CIO) {
            install(plugin = ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "pokeapi.co"
                }
            }
            expectSuccess = true
        }
    }

    single<PokeApiService> {
        PokeApiServiceImpl(
            client = get<HttpClient>(),
        )
    }

    single<PokemonRepository> { PokemonRepositoryImpl(api = get<PokeApiService>()) }

    factory { GetPokemonListUseCase(repository = get<PokemonRepository>()) }
    factory { GetPokemonDetailUseCase(repository = get<PokemonRepository>()) }
}
