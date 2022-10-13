package io.github.cianciustyles

import org.junit.Assert.*
import org.junit.Test

class XzRectTest {
    @Test
    fun testHitBeforeTMin() {
        val xzRect = XzRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(26.0, 3.0, 6.0),
            Vector3(0.0, 2.0, 0.0),
            0.0
        )

        val hitRecord = xzRect.hit(ray, 1.0, 2.0)
        assertNull(hitRecord)
    }

    @Test
    fun testHitAfterTMax() {
        val xzRect = XzRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(26.0, 1.0, 10.0),
            Vector3(0.0, 1.0, -1.0),
            0.0
        )

        val hitRecord = xzRect.hit(ray, 1.0, 2.0)
        assertNull(hitRecord)
    }

    @Test
    fun testHitXLessThanX0() {
        val xzRect = XzRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(-4.0, 3.0, 4.0),
            Vector3(1.0, 1.0, 1.0),
            1.0
        )

        val hitRecord = xzRect.hit(ray, 0.0, 2.0)
        assertNull(hitRecord)
    }

    @Test
    fun testHitXGreaterThanX1() {
        val xzRect = XzRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(3.0, 3.0, 4.0),
            Vector3(1.0, 1.0, 1.0),
            1.0
        )

        val hitRecord = xzRect.hit(ray, 0.0, 2.0)
        assertNull(hitRecord)
    }

    @Test
    fun testHitZLessThanZ0() {
        val xzRect = XzRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(1.0, 3.0, -1.0),
            Vector3(0.0, 1.0, 0.0),
            1.0
        )

        val hitRecord = xzRect.hit(ray, 0.0, 2.0)
        assertNull(hitRecord)
    }

    @Test
    fun testHitZGreaterThanZ1() {
        val xzRect = XzRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(1.0, 3.0, 4.0),
            Vector3(0.0, 1.0, 0.0),
            1.0
        )

        val hitRecord = xzRect.hit(ray, 0.0, 2.0)
        assertNull(hitRecord)
    }

    @Test
    fun testHit() {
        val xzRect = XzRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(1.0, 3.0, 1.0),
            Vector3(0.0, 1.0, 1.0),
            1.0
        )

        val hitRecord = xzRect.hit(ray, 0.0, 2.0)
        assertNotNull(hitRecord)
    }
}