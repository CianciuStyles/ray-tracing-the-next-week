package io.github.cianciustyles

import org.junit.Assert.assertEquals
import org.junit.Test

class MovingSphereTest {
    @Test
    fun testCenter() {
        val sphere = MovingSphere(
            Point3(0.0, 0.0, 0.0),
            Point3(2.0, 0.0, 0.0),
            0.0,
            1.0,
            5.0,
            Lambertian()
        )

        val newCenter = sphere.center(0.5)
        assertEquals(newCenter, Point3(1.0, 0.0, 0.0))
    }
}