package io.github.cianciustyles

import org.junit.Assert.*
import org.junit.Test

class MetalTest {
    @Test
    fun testScatterNull() {
        val metal = Metal(fuzz = 0.0)
        val normal = Vector3(0.0, 0.0, -1.0)
        val hitRecord = HitRecord(
            point = Point3(),
            normal = normal,
            material = metal,
            t = 0.0,
            u = 0.0,
            v = 0.0,
            frontFace = true
        )
        val rayIn = Ray(direction = Vector3(z = -1.0))

        val (color, rayOut) = metal.scatter(rayIn, hitRecord)

        assertNull(color)
        assertNull(rayOut)
    }

    @Test
    fun testScatter() {
        val metal = Metal(fuzz = 0.0)
        val normal = Vector3(0.0, 0.0, -1.0)
        val hitRecord = HitRecord(
            point = Point3(),
            normal = normal,
            material = metal,
            t = 0.0,
            u = 0.0,
            v = 0.0,
            frontFace = true
        )
        val rayIn = Ray(
            direction = Vector3(z = 1.0),
            time = 1.5
        )

        val (color, rayOut) = metal.scatter(rayIn, hitRecord)

        assertNotNull(color)
        assertEquals(Color.BLACK, color)

        assertNotNull(rayOut)
        assertNotEquals(rayIn, rayOut!!)
        assertEquals(Point3(), rayOut.origin)
        assertEquals(normal, rayOut.direction)
        assertEquals(rayIn.time, rayOut.time, Math.ulp(rayIn.time))
    }
}