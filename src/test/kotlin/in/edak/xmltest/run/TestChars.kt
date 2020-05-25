package `in`.edak.xmltest.run

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class TestChars {
    @Test
    fun testNotAscii() {
        assertTrue(isAscii("Hello, World!"))
        assertFalse(isAscii("Привет, Мир!"))

        println("Привет!".toByteArray(Charsets.UTF_16).toList())
        println("Привет!".toByteArray(Charsets.UTF_16BE).toList())
        println("Привет!".toByteArray(Charsets.UTF_16LE).toList())
    }

    fun isAscii(s: String): Boolean {
        return s.toCharArray().find { ch -> ch.toInt().let { i -> i < 0 || i > 127 } } == null
    }
}