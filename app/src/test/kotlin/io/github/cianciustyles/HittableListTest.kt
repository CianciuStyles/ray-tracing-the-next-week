package io.github.cianciustyles

import org.junit.Assert.*
import org.junit.Test

class HittableListTest {
    @Test
    fun testDefaultConstructor() {
        val hittableList = HittableList()

        assertEquals(0, hittableList.objects.size)
    }

    @Test
    fun testConstructor() {
        val hittableList = HittableList(mutableListOf(Sphere(material = Lambertian())))

        assertEquals(1, hittableList.objects.size)
    }

    @Test
    fun testAdd() {
        val hittableList = HittableList()
        assertEquals(0, hittableList.objects.size)

        hittableList.add(Sphere(material = Lambertian()))
        assertEquals(1, hittableList.objects.size)
    }

    @Test
    fun testClear() {
        val hittableList = HittableList(mutableListOf(Sphere(material = Lambertian())))
        assertEquals(1, hittableList.objects.size)

        hittableList.clear()
        assertEquals(0, hittableList.objects.size)
    }

    @Test
    fun testHitBeforeTmin() {
        val sphere = Sphere(
            center = Point3(0.0, 0.0, 1.0),
            radius = 0.5,
            material = Lambertian()
        )
        val hittableList = HittableList(mutableListOf(sphere))
        val ray = Ray(
            origin = Point3(0.0, 0.0, -1.0),
            direction = Vector3(0.0, 0.0, 1.0)
        )

        assertNull(hittableList.hit(ray, 0.9, 1.1))
    }

    @Test
    fun testHitBetweenTminAndTmax() {
        val sphere = Sphere(
            center = Point3(0.0, 0.0, 1.0),
            radius = 0.5,
            material = Lambertian()
        )
        val hittableList = HittableList(mutableListOf(sphere))
        val ray = Ray(
            origin = Point3(0.0, 0.0, -1.0),
            direction = Vector3(0.0, 0.0, 1.0)
        )

        assertNotNull(hittableList.hit(ray, 0.2, 1.8))
    }

    @Test
    fun testHitAfterTmax() {
        val sphere = Sphere(
            center = Point3(0.0, 0.0, 1.0),
            radius = 0.5,
            material = Lambertian()
        )
        val hittableList = HittableList(mutableListOf(sphere))
        val ray = Ray(
            origin = Point3(0.0, 0.0, -1.0),
            direction = Vector3(0.0, 0.0, 1.0)
        )

        assertNull(hittableList.hit(ray, 0.2, 0.3))
    }

    @Test
    fun testBoundingBoxEmptyList() {
        val list = HittableList()

        val boundingBox = list.boundingBox(0.0, 1.0)
        assertNull(boundingBox)
    }

    @Test
    fun testBoundingBoxFullList() {
        val list = HittableList(mutableListOf(
            Sphere(
                Point3(),
                1.0,
                Lambertian()
            ),
            Sphere(
                Point3(5.0, 5.0, 5.0),
                2.0,
                Lambertian()
            )
        ))

        val boundingBox = list.boundingBox(0.0, 1.0)
        assertEquals(
            Aabb(Point3(-1.0, -1.0, -1.0), Point3(7.0, 7.0, 7.0)),
            boundingBox
        )
    }
}