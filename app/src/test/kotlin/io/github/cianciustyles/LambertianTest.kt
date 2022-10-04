package io.github.cianciustyles

import org.junit.Assert.*
import org.junit.Test

class LambertianTest {
    @Test
    fun testScatter() {
        val normal = Vector3(1.0, 0.0, 1.0)
        val point = Point3()
        val color = Color(0.5, 0.6, 0.7)
        val lambertian = Lambertian(color)
        val hitRecord = HitRecord(
            point,
            normal,
            lambertian,
            0.0,
            0.25,
            0.5,
            true
        )
        val rayIn = Ray(time = 1.5)

        val (albedo, rayOut) = lambertian.scatter(rayIn, hitRecord)

        assertEquals(albedo, color)
        assertNotEquals(rayIn, rayOut)
        assertEquals(point, rayOut.origin)
        assertNotEquals(normal, rayOut.direction)
        assertEquals(rayIn.time, rayOut.time, Math.ulp(rayIn.time))
    }
}