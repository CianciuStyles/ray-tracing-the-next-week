package io.github.cianciustyles

abstract class Hittable {
    abstract fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord?
    abstract fun boundingBox(time0: Double, time1: Double): Aabb?

    fun hitRecord(ray: Ray, outwardNormal: Vector3, material: Material, t: Double, u: Double, v: Double) =
        hitRecord(ray.at(t), ray, outwardNormal, material, t, u, v)

    fun hitRecord(point: Point3, ray: Ray, outwardNormal: Vector3, material: Material, t: Double, u: Double, v: Double): HitRecord {
        val frontFace = ray.direction dot outwardNormal < 0
        val normal = if (frontFace) outwardNormal else -outwardNormal

        return HitRecord(point, normal, material, t, u, v, frontFace)
    }
}