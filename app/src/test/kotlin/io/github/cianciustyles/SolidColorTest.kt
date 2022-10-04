package io.github.cianciustyles

import org.junit.Assert.*
import org.junit.Test

class SolidColorTest {
    @Test
    fun testPrimaryConstructor() {
        val color = Color(1.0, 0.0, 0.5)
        val solidColor = SolidColor(color)

        assertEquals(color, solidColor.value(0.1, 0.0, Point3()))
    }

    @Test
    fun testSecondaryConstructor() {
        val red = 0.4
        val green = 0.5
        val blue = 0.2
        val solidColor = SolidColor(red, green, blue)

        val color = solidColor.value(0.2, 0.4, Point3())
        assertEquals(red, color.r, Math.ulp(0.0))
        assertEquals(green, color.g, Math.ulp(0.0))
        assertEquals(blue, color.b, Math.ulp(0.0))
    }
}