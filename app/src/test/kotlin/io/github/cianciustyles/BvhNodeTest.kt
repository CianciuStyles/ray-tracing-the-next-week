package io.github.cianciustyles

import org.junit.Assert.*
import org.junit.Test

class BvhNodeTest {
    @Test
    fun testHitLeft() {
        val hittableList = HittableList(mutableListOf(
            Sphere(
                center = Point3(0.0, 0.0, 1.0),
                radius = 1.0,
                material = Lambertian()
            ),
            Sphere(
                center = Point3(4.0, 4.0, 1.0),
                radius = 1.0,
                material = Lambertian()
            )
        ))
        val node = BvhNode(
            hittableList = hittableList,
            0.0,
            1.0
        )
        val ray = Ray(
            Point3(),
            Vector3(0.0, 0.0, 1.0)
        )
        val hitRecord = node.hit(ray, 0.0, 1.0)
        assertNotNull(hitRecord)
    }

    @Test
    fun testHitRight() {
        val hittableList = HittableList(mutableListOf(
            Sphere(
                center = Point3(-4.0, -4.0, 1.0),
                radius = 1.0,
                material = Lambertian()
            ),
            Sphere(
                center = Point3(0.0, 0.0, 1.0),
                radius = 1.0,
                material = Lambertian()
            )
        ))
        val node = BvhNode(
            hittableList = hittableList,
            0.0,
            1.0
        )
        val ray = Ray(
            Point3(),
            Vector3(0.0, 0.0, 1.0)
        )
        val hitRecord = node.hit(ray, 0.0, 1.0)
        assertNotNull(hitRecord)
    }

    @Test
    fun testNoHit() {
        val hittableList = HittableList(mutableListOf(
            Sphere(
                center = Point3(-4.0, -4.0, 1.0),
                radius = 1.0,
                material = Lambertian()
            ),
            Sphere(
                center = Point3(4.0, 4.0, 1.0),
                radius = 1.0,
                material = Lambertian()
            )
        ))
        val node = BvhNode(
            hittableList = hittableList,
            0.0,
            1.0
        )
        val ray = Ray(
            Point3(),
            Vector3(0.0, 0.0, 1.0)
        )
        val hitRecord = node.hit(ray, 0.0, 1.0)
        assertNull(hitRecord)
    }
}