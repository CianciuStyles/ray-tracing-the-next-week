package io.github.cianciustyles

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class DielectricTest {
    @Test
    fun testScatterReflect() {
        val point = Point3()
        val normal = Vector3(1.0, 0.0, 0.0)
        val glass = Dielectric(1.5)
        val hitRecord = HitRecord(
            point = point,
            normal = normal,
            material = glass,
            0.0,
            0.0,
            0.0,
            true
        )
        val rayIn = Ray(
            direction = Vector3(z = 1.0),
            time = 0.5
        )

        val (color, rayOut) = glass.scatter(rayIn, hitRecord)

        assertEquals(Color.WHITE, color)
        assertNotEquals(rayIn, rayOut)
        assertEquals(point, rayOut.origin)
        assertNotEquals(normal, rayOut.direction)
        assertEquals(rayIn.time, rayOut.time, Math.ulp(rayIn.time))
    }
}