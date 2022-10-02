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

    @Test
    fun testBoundingBox() {
        val time0 = 0.0
        val time1 = 1.0

        val sphere = MovingSphere(
            center0 = Point3(),
            center1 = Point3(2.0, 2.0, 2.0),
            time0,
            time1,
            0.5,
            Lambertian()
        )

        val boundingBox = sphere.boundingBox(time0, time1)
        assertEquals(
            Aabb(Point3(-0.5, -0.5, -0.5), Point3(2.5, 2.5, 2.5)),
            boundingBox
        )
    }
}