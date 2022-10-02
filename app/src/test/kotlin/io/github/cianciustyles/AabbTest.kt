package io.github.cianciustyles

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.test.assertEquals

class AabbTest {
    @Test
    fun testHitFalse() {
        val aabb = Aabb(
            minimum = Point3(0.0, 0.0, 0.0),
            maximum = Point3(2.0, 2.0, 0.0)
        )
        val ray = Ray(
            origin = Point3(1.0, 1.0, -1.0),
            direction = Vector3(0.0, 0.0, 2.0)
        )

        assertFalse(aabb.hit(ray, 0.0, 5.0))
    }

    @Test
    fun testHitTrue() {
        val aabb = Aabb(
            minimum = Point3(0.0, 0.0, 0.0),
            maximum = Point3(1.0, 2.0, 2.0)
        )
        val ray = Ray(
            origin = Point3(0.5, 1.0, -1.0),
            direction = Vector3(0.0, 0.0, 1.0)
        )

        assertTrue(aabb.hit(ray, 0.0, 2.0))
    }

    @Test
    fun testSurroundingBox() {
        val aabb0 = Aabb(
            Point3(),
            Point3(1.0, 1.0, 1.0)
        )
        val aabb1 = Aabb(
            Point3(2.0, 2.0, 2.0),
            Point3(3.0, 3.0, 3.0)
        )

        val surroundingBox = Aabb.surroundingBox(aabb0, aabb1)
        assertEquals(
            Aabb(Point3(), Point3(3.0, 3.0, 3.0)),
            surroundingBox
        )
    }
}