package com.nectarofthepeople.spectoroster

import com.nectarofthepeople.spectoroster.data.repository.MovieRepository
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieMappingUnitTest {

    @Test
    fun parseYear_validDate() {
        val year = MovieRepository.parseYear("2023-05-12")
        assertEquals(2023, year)
    }

    @Test
    fun parseYear_nullOrBlank() {
        assertEquals(0, MovieRepository.parseYear(null))
        assertEquals(0, MovieRepository.parseYear(""))
        assertEquals(0, MovieRepository.parseYear("   "))
    }

    @Test
    fun parseShortDescription_taglinePresent() {
        val desc = MovieRepository.parseShortDescription("A great tagline.", "This is the overview text.")
        assertEquals("A great tagline.", desc)
    }

    @Test
    fun parseShortDescription_taglineBlank_usesOverview() {
        val desc1 = MovieRepository.parseShortDescription("", "This is the overview text.")
        assertEquals("This is the overview text.", desc1)

        val desc2 = MovieRepository.parseShortDescription(null, "This is the overview text.")
        assertEquals("This is the overview text.", desc2)
    }

    @Test
    fun parseShortDescription_bothBlankOrNull() {
        val desc = MovieRepository.parseShortDescription(null, null)
        assertEquals("", desc)
    }
}
