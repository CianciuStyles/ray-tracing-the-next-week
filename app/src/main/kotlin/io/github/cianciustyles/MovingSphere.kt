package io.github.cianciustyles

import kotlin.math.sqrt

class MovingSphere(
    private val center0: Point3,
    private val center1: Point3,
    private val time0: Double,
    private val time1: Double,
    private val radius: Double,
    private val material: Material
) : Hittable() {
    override fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord? {
        val oc = ray.origin - center(ray.time)
        val a = ray.direction.lengthSquared()
        val halfB = oc dot ray.direction
        val c = oc.lengthSquared() - radius * radius

        val discriminant = halfB * halfB - a * c
        if (discriminant < 0) return null
        val sqrtd = sqrt(discriminant)

        // Find the nearest root that lies in the acceptable range
        var root = (-halfB - sqrtd) / a
        if (root < tMin || tMax < root) {
            root = (-halfB + sqrtd) / a
            if (root < tMin || tMax > root) return null
        }

        return hitRecord(ray, (ray.at(root) - center(ray.time)) / radius, material, root)
    }

    fun center(time: Double): Point3 =
        center0 + (center1 - center0) * ((time - time0) / (time1 - time0))
}