package com.menilv.feature

import com.menilv.feature.home.adapter.SearchItem
import org.junit.Test

class SearchItemTest {

    @Test
    fun `For given search item property model should return expecting id as string`() {
        // Given
        val name = "Google"
        val kind = "creator"
        val image = "www.google.ba"

        // When
        val searchItem = SearchItem(name, kind, image)

        // Then
        assert(searchItem.id() == "Googlecreatorwww.google.ba")
    }
}
