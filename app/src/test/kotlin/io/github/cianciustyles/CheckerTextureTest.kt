package io.github.cianciustyles

import org.junit.Assert.*
import org.junit.Test

class CheckerTextureTest {
    @Test
    fun testPrimaryConstructor() {
        val texture1 = SolidColor(Color.BLACK)
        val texture2 = SolidColor(Color.BLUE)
        val checkerTexture = CheckerTexture(texture1, texture2)

        assertEquals(texture1, checkerTexture.even)
        assertEquals(texture2, checkerTexture.odd)
    }

    @Test
    fun testSecondaryConstructor() {
        val checkerTexture = CheckerTexture(Color.WHITE, Color.BLACK)

        assertEquals(Color.WHITE, checkerTexture.even.value(0.0, 0.0, Point3()))
        assertEquals(Color.BLACK, checkerTexture.odd.value(1.0, 1.0, Point3()))
    }

    @Test
    fun testValue() {
        val checkerTexture = CheckerTexture(Color.WHITE, Color.BLACK)

        assertEquals(Color.WHITE, checkerTexture.value(0.0, 0.0, Point3()))
        assertEquals(Color.BLACK, checkerTexture.value(0.0, 0.0, Point3(1.0, 1.0, 1.0)))
    }
}