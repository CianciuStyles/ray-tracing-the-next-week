package io.github.cianciustyles

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class IsotropicTest {
    @Test
    fun testPrimaryConstructor() {
        val albedo = NoiseTexture(2.0)

        val isotropic = Isotropic(albedo)

        assertEquals(albedo, isotropic.albedo)
    }

    @Test
    fun testSecondaryConstructor() {
        val color = Color.BLUE

        val isotropic = Isotropic(color)

        assertEquals(SolidColor(color), isotropic.albedo)
    }

    @Test
    fun testScatter() {
        val rayInTime = 3.5
        val rayIn = Ray(time = rayInTime)

        val hitPoint = Point3(2.0, 1.0, 3.0)
        val hitRecord = HitRecord(
            hitPoint,
            Vector3(),
            Lambertian(),
            0.0,
            0.0,
            0.0,
            true
        )

        val isotropic = Isotropic(Color.BLACK)
        val (attenuation, scatter) = isotropic.scatter(rayIn, hitRecord)

        assertNotNull(attenuation)
        assertEquals(Color.BLACK, attenuation)

        assertNotNull(scatter)
        assertEquals(hitPoint, scatter!!.origin)
        assertEquals(rayInTime, scatter.time, Math.ulp(rayInTime))
    }
}