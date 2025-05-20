package com.app.capitalone_pokeapi_app

import android.app.Application
import com.app.capitalone_pokeapi_app.di.networkModule
import com.app.capitalone_pokeapi_app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CapitalOnePokemonApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CapitalOnePokemonApp)
            modules(networkModule, viewModelModule)
        }
    }
}