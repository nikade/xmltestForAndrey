package `in`.edak.xmltest.run

import org.junit.Test

import org.junit.Assert.*
import java.util.*

class TestChars {
    @Test
    fun testNotAscii() {
        assertTrue(isAscii("Hello, World!"))
        assertFalse(isAscii("Привет, Мир!"))
    }

    fun isAscii(s: String): Boolean {
        return s.toCharArray().find { ch -> ch.toInt().let { i -> i<0 || i>127 } } == null
    }
}