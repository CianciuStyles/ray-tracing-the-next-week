package io.github.cianciustyles

import org.junit.Assert.*
import org.junit.Test

class YzRectTest {
    @Test
    fun testHitBeforeTMin() {
        val yzRect = YzRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(26.0, 3.0, 6.0),
            Vector3(0.0, 2.0, 0.0),
            0.0
        )

        val hitRecord = yzRect.hit(ray, 1.0, 2.0)
        assertNull(hitRecord)
    }

    @Test
    fun testHitAfterTMax() {
        val yzRect = YzRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(1.0, 3.0, 10.0),
            Vector3(1.0, 2.0, -1.0),
            0.0
        )

        val hitRecord = yzRect.hit(ray, 1.0, 2.0)
        assertNull(hitRecord)
    }

    @Test
    fun testHitZLessThanZ0() {
        val yzRect = YzRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(2.0, 1.0, -4.0),
            Vector3(1.0, 0.0, 1.0),
            1.0
        )

        val hitRecord = yzRect.hit(ray, 0.0, 2.0)
        assertNull(hitRecord)
    }

    @Test
    fun testHitZGreaterThanZ1() {
        val yzRect = YzRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(2.0, 1.0, 4.0),
            Vector3(1.0, 0.0, 1.0),
            1.0
        )

        val hitRecord = yzRect.hit(ray, 0.0, 2.0)
        assertNull(hitRecord)
    }

    @Test
    fun testHitYLessThanY0() {
        val yzRect = YzRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(3.0, -3.0, 1.0),
            Vector3(1.0, 1.0, 1.0),
            1.0
        )

        val hitRecord = yzRect.hit(ray, 0.0, 2.0)
        assertNull(hitRecord)
    }

    @Test
    fun testHitYGreaterThanY1() {
        val yzRect = YzRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(3.0, 3.0, 1.0),
            Vector3(1.0, 1.0, 1.0),
            1.0
        )

        val hitRecord = yzRect.hit(ray, 0.0, 2.0)
        assertNull(hitRecord)
    }

    @Test
    fun testHit() {
        val yzRect = YzRect(Lambertian(), 0.0, 2.0, 0.0, 2.0, 4.0)
        val ray = Ray(
            Point3(2.0, 1.0, -1.0),
            Vector3(1.0, 0.0, 1.0),
            1.0
        )

        val hitRecord = yzRect.hit(ray, 0.0, 2.0)
        assertNotNull(hitRecord)
    }
}