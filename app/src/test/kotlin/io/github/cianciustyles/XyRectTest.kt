package io.github.cianciustyles

import org.junit.Assert.*
import org.junit.Test

class XyRectTest {
    @Test
    fun testHitBeforeTMin() {
        val xyRect = XyRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(26.0, 3.0, 6.0),
            Vector3(0.0, 2.0, 0.0),
            0.0
        )

        val hitRecord = xyRect.hit(ray, 1.0, 2.0)
        assertNull(hitRecord)
    }

    @Test
    fun testHitAfterTMax() {
        val xyRect = XyRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(26.0, 3.0, 10.0),
            Vector3(0.0, 2.0, -1.0),
            0.0
        )

        val hitRecord = xyRect.hit(ray, 1.0, 2.0)
        assertNull(hitRecord)
    }

    @Test
    fun testHitXLessThanX0() {
        val xyRect = XyRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(-4.0, 3.0, 4.0),
            Vector3(1.0, 0.0, 1.0),
            1.0
        )

        val hitRecord = xyRect.hit(ray, 0.0, 2.0)
        assertNull(hitRecord)
    }

    @Test
    fun testHitXGreaterThanX1() {
        val xyRect = XyRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(3.0, 3.0, 4.0),
            Vector3(1.0, 0.0, 1.0),
            1.0
        )

        val hitRecord = xyRect.hit(ray, 0.0, 2.0)
        assertNull(hitRecord)
    }

    @Test
    fun testHitYLessThanY0() {
        val xyRect = XyRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(1.0, -3.0, 4.0),
            Vector3(0.0, 1.0, 1.0),
            1.0
        )

        val hitRecord = xyRect.hit(ray, 0.0, 2.0)
        assertNull(hitRecord)
    }

    @Test
    fun testHitYGreaterThanY1() {
        val xyRect = XyRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(1.0, 3.0, 4.0),
            Vector3(0.0, 1.0, 1.0),
            1.0
        )

        val hitRecord = xyRect.hit(ray, 0.0, 2.0)
        assertNull(hitRecord)
    }

    @Test
    fun testHit() {
        val xyRect = XyRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(1.0, 1.0, 4.0),
            Vector3(0.0, 0.0, 1.0),
            1.0
        )

        val hitRecord = xyRect.hit(ray, 0.0, 2.0)
        assertNotNull(hitRecord)
    }
}