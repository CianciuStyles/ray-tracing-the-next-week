package io.github.cianciustyles

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import kotlin.math.sqrt

class RotateYTest {
    @Test
    fun testBoundingBox() {
        val box = Box(Lambertian(), Point3(), Point3(2.0, 2.0, 2.0))

        val rotate = RotateY(box, 45.0)

        val boundingBox = rotate.boundingBox(0.0, 1.0)
        assertNotNull(boundingBox)
        assertEquals(0.0, boundingBox!!.minimum.x, Math.ulp(0.0))
        assertEquals(0.0, boundingBox.minimum.y, Math.ulp(0.0))
        assertEquals(-sqrt(2.0), boundingBox.minimum.z, Math.ulp(sqrt(2.0)))
        assertEquals(2 * sqrt(2.0), boundingBox.maximum.x, Math.ulp(2 * sqrt(2.0)))
        assertEquals(2.0, boundingBox.maximum.y, Math.ulp(2.0))
        assertEquals(sqrt(2.0), boundingBox.maximum.z, Math.ulp(sqrt(2.0)))
    }

    @Test
    fun testNoHit() {
        val box = Box(Lambertian(), Point3(), Point3(2.0, 2.0, 2.0))
        val ray = Ray(
            Point3(-1.0, -1.0, -1.0),
            Vector3(0.0, 0.0, 1.0)
        )
        val rotate = RotateY(box, 45.0)

        assertNull(rotate.hit(ray, 0.0, 1.0))
    }

    @Test
    fun testHit() {
        val box = Box(Lambertian(), Point3(), Point3(2.0, 2.0, 2.0))
        val ray = Ray(
            Point3(1.0, 1.0, -1.0),
            Vector3(0.0, 0.0, 1.0)
        )
        val rotate = RotateY(box, 45.0)

        assertNotNull(rotate.hit(ray, 0.0, 1.0))
    }
}