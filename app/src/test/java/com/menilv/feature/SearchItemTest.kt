package com.menilv.feature.home.adapter

import org.junit.Test

class SearchItemTest {

    @Test
    fun testInitialState() {
        // Given
        val name = "Google"
        val kind = "creator"
        val image = "www.google.ba"

        // When
        val searchItem = SearchItem(name,kind,image)

        // Then
        assert(searchItem.id() == "Googlecreatorwww.google.ba")
    }
}