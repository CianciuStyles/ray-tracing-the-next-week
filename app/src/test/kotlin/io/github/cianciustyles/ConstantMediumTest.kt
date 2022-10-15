package io.github.cianciustyles

import org.junit.Assert.assertEquals
import org.junit.Test

class ConstantMediumTest {
    @Test
    fun testPrimaryConstructor() {
        val boundary = Box(Lambertian(), Point3(), Point3(2.0, 2.0, 2.0))
        val density = 2.0
        val texture = NoiseTexture(3.0)

        val medium = ConstantMedium(boundary, density, texture)

        assertEquals(boundary, medium.boundary)
        assertEquals(-0.5, medium.negativeInverseDensity, Math.ulp(-0.5))
        assertEquals(texture, medium.phaseFunction.albedo)
    }

    @Test
    fun testSecondaryConstructor() {
        val boundary = Box(Lambertian(), Point3(), Point3(2.0, 2.0, 2.0))
        val density = 2.0
        val color = Color.BLACK

        val medium = ConstantMedium(boundary, density, color)

        assertEquals(boundary, medium.boundary)
        assertEquals(-0.5, medium.negativeInverseDensity, Math.ulp(-0.5))
        assertEquals(SolidColor(color), medium.phaseFunction.albedo)
    }

    @Test
    fun testBoundingBox() {
        val boundary = Box(Lambertian(), Point3(), Point3(2.0, 2.0, 2.0))
        val medium = ConstantMedium(boundary, 1.5, Color.WHITE)

        val time0 = 0.0
        val time1 = 0.5

        assertEquals(
            boundary.boundingBox(time0, time1),
            medium.boundingBox(time0, time1)
        )
    }
}