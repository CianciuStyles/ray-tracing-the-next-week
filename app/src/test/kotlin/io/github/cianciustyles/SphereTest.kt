package io.github.cianciustyles

import org.junit.Assert.*
import org.junit.Test

class SphereTest {
    @Test
    fun testHitBeforeTmin() {
        val sphere = Sphere(
            Point3(0.0, 0.0, 1.0),
            0.5,
            Lambertian()
        )
        val ray = Ray(
            Point3(0.0, 0.0, -1.0),
            Vector3(0.0, 0.0, 1.0)
        )

        assertNull(sphere.hit(ray, 0.9, 1.1))
    }

    @Test
    fun testHitBetweenTminAndTmax() {
        val sphere = Sphere(
            Point3(0.0, 0.0, 1.0),
            0.5,
            Lambertian()
        )
        val ray = Ray(
            Point3(0.0, 0.0, -1.0),
            Vector3(0.0, 0.0, 1.0)
        )

        assertNotNull(sphere.hit(ray, 0.2, 1.8))
    }

    @Test
    fun testHitAfterTmax() {
        val sphere = Sphere(
            Point3(0.0, 0.0, 1.0),
            0.5,
            Lambertian()
        )
        val ray = Ray(
            Point3(0.0, 0.0, -1.0),
            Vector3(0.0, 0.0, 1.0)
        )

        assertNull(sphere.hit(ray, 0.2, 0.3))
    }

    @Test
    fun testHitRecordFrontFace() {
        val material = Lambertian()
        val sphere = Sphere(
            Point3(0.0, 0.0, 1.0),
            0.5,
            material
        )
        val ray = Ray(
            Point3(0.0, 0.0, -1.0),
            Vector3(0.0, 0.0, 1.0)
        )

        val outwardNormal = Vector3(0.0, 0.0, -1.0)
        val hitRecord = sphere.hitRecord(ray, outwardNormal, material, 0.5, 0.75, 0.5)

        assertEquals(outwardNormal, hitRecord.normal)
        assertTrue(hitRecord.frontFace)
    }

    @Test
    fun testHitRecordBackFace() {
        val material = Lambertian()
        val sphere = Sphere(
            Point3(0.0, 0.0, 1.0),
            0.5,
            material
        )
        val ray = Ray(
            Point3(0.0, 0.0, -1.0),
            Vector3(0.0, 0.0, 1.0)
        )

        val outwardNormal = Vector3(0.0, 0.0, 1.0)
        val hitRecord = sphere.hitRecord(ray, outwardNormal, material, 1.5, 0.25, 0.5)

        assertEquals(-outwardNormal, hitRecord.normal)
        assertFalse(hitRecord.frontFace)
    }

    @Test
    fun testBoundingBox() {
        val sphere = Sphere(
            center = Point3(),
            radius = 2.0,
            Lambertian()
        )

        val boundingBox = sphere.boundingBox(0.0, 1.0)
        assertEquals(
            Aabb(Point3(-2.0, -2.0, -2.0), Point3(2.0, 2.0, 2.0)),
            boundingBox
        )
    }

    @Test
    fun testGetSphereUV() {
        val (u1, v1) = Sphere.getSphereUV(Vector3(-1.0, 0.0, 0.0))
        assertEquals(0.0, u1, Math.ulp(0.0))
        assertEquals(v1, 0.5, Math.ulp(0.5))

        val (u2, v2) = Sphere.getSphereUV(Vector3(1.0, 0.0, 0.0))
        assertEquals(0.5, u2, Math.ulp(0.5))
        assertEquals(0.5, v2, Math.ulp(0.5))

        val (u3, v3) = Sphere.getSphereUV(Vector3(0.0, -1.0, 0.0))
        assertEquals(0.5, u3, Math.ulp(0.5))
        assertEquals(0.0, v3, Math.ulp(0.0))

        val (u4, v4) = Sphere.getSphereUV(Vector3(0.0, 1.0, 0.0))
        assertEquals(0.5, u4, Math.ulp(0.5))
        assertEquals(1.0, v4, Math.ulp(1.0))

        val (u5, v5) = Sphere.getSphereUV(Vector3(0.0, 0.0, -1.0))
        assertEquals(0.75, u5, Math.ulp(0.75))
        assertEquals(0.5, v5, Math.ulp(0.5))

        val (u6, v6) = Sphere.getSphereUV(Vector3(0.0, 0.0, 1.0))
        assertEquals(0.25, u6, Math.ulp(0.25))
        assertEquals(0.5, v6, Math.ulp(0.5))
    }
}