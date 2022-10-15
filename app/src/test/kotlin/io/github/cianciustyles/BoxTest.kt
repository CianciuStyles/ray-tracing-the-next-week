package io.github.cianciustyles

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class BoxTest {
    @Test
    fun testBoundingBox() {
        val boxMin = Point3()
        val boxMax = Point3(2.0, 2.0, 0.0)

        val box = Box(Lambertian(), boxMin, boxMax)
        val boundingBox = box.boundingBox(0.0, 1.0)

        assertNotNull(boundingBox)
        assertEquals(boxMin, boundingBox.minimum)
        assertEquals(boxMax, boundingBox.maximum)
    }

    @Test
    fun testHit() {
        val boxMin = Point3()
        val boxMax = Point3(2.0, 2.0, 2.0)
        val ray = Ray(
            origin = Point3(1.0, 1.0, -1.0),
            direction = Vector3(0.0, 0.0, 1.0)
        )

        val box = Box(Lambertian(), boxMin, boxMax)
        val hitRecord = box.hit(ray, 0.0, 5.0)

        assertNotNull(hitRecord)
        assertEquals(Point3(1.0, 1.0, 0.0), hitRecord?.point)
    }

    @Test
    fun testNoHit() {
        val boxMin = Point3()
        val boxMax = Point3(2.0, 2.0, 2.0)
        val ray = Ray(
            origin = Point3(3.0, 3.0, -3.0),
            direction = Vector3(0.0, 0.0, 1.0)
        )

        val box = Box(Lambertian(), boxMin, boxMax)
        val hitRecord = box.hit(ray, 0.0, 5.0)

        assertNull(hitRecord)
    }
}