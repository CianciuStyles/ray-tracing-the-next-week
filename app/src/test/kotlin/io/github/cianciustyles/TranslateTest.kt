package io.github.cianciustyles

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class TranslateTest {
    @Test
    fun testBoundingBox() {
        val box = Box(Lambertian(), Point3(), Point3(2.0, 2.0, 2.0))
        val displacement = Vector3(1.0, 0.0, 0.0)

        val translate = Translate(box, displacement)

        val boundingBox = translate.boundingBox(0.0, 1.0)
        assertNotNull(boundingBox)
        assertEquals(box.boundingBox(0.0, 1.0).minimum + displacement, boundingBox!!.minimum)
        assertEquals(box.boundingBox(0.0, 1.0).maximum + displacement, boundingBox.maximum)
    }

    @Test
    fun testNoHit() {
        val box = Box(Lambertian(), Point3(), Point3(2.0, 2.0, 2.0))
        val displacement = Vector3(2.0, 0.0, 0.0)
        val ray = Ray(Point3(1.0, 1.0, -1.0), Vector3(z = 1.0), 0.0)

        val translate = Translate(box, displacement)

        assertNull(translate.hit(ray, 1.0, 5.0))
    }

    @Test
    fun testHit() {
        val box = Box(Lambertian(), Point3(), Point3(2.0, 2.0, 2.0))
        val displacement = Vector3(0.5, 0.0, 0.0)
        val ray = Ray(Point3(1.0, 1.0, -1.0), Vector3(z = 1.0), 0.0)

        val translate = Translate(box, displacement)

        assertNotNull(translate.hit(ray, 1.0, 5.0))
    }
}