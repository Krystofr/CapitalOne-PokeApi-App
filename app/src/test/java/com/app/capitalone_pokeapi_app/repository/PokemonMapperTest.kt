package com.app.capitalone_pokeapi_app.repository

import com.app.capitalone_pokeapi_app.domain.model.PokemonDto
import com.app.capitalone_pokeapi_app.domain.model.toDomain
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PokemonMapperTest {

    @Test
    fun `PokemonDto toDomain maps correctly`() {
        val dto = PokemonDto(name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/")
        val domain = dto.toDomain()

        assertEquals(1, domain.id)
        assertEquals("Bulbasaur", domain.name)
        assertTrue(domain.imageUrl.contains("/1.png"))
    }
}
