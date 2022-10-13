package io.github.cianciustyles

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class DiffuseLightTest {
    @Test
    fun testPrimaryConstructor() {
        val texture = SolidColor(Color.BLUE)

        val diffuseLight = DiffuseLight(texture)

        assertEquals(Color.BLUE, diffuseLight.emitted(0.0, 0.0, Point3()))
    }

    @Test
    fun testSecondaryConstructor() {
        val diffuseLight = DiffuseLight(Color.BLUE)

        assertEquals(Color.BLUE, diffuseLight.emitted(0.0, 0.0, Point3()))
    }

    @Test
    fun testScatter() {
        val diffuseLight = DiffuseLight(Color.WHITE)
        val rayIn = Ray()
        val time = 0.0
        val hitRecord = HitRecord(
            rayIn.at(time),
            Vector3(),
            Lambertian(),
            time,
            0.0,
            0.0,
            true
        )
        
        val (attenuation, scatter) = diffuseLight.scatter(rayIn, hitRecord)

        assertNull(attenuation)
        assertNull(scatter)
    }
}