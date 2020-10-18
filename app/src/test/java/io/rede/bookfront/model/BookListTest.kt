package io.rede.bookfront.model

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class BookListTest : TestCase() {

    @Test
    fun testGetListNameEncoded() {
        Assert.assertEquals("combined-print-and-e-book-fiction", "combined-print-and-e-book-fiction")
    }

    @Test
    fun testGetDisplayName() {
        Assert.assertEquals("Culture", "Culture")
    }

}