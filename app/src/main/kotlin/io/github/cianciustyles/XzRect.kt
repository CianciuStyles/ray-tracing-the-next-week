package io.github.cianciustyles

class XzRect(
    private val material: Material,
    private val x0: Double,
    private val x1: Double,
    private val z0: Double,
    private val z1: Double,
    private val k: Double
): Hittable() {
    override fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord? {
        val t = (k - ray.origin.y) / ray.direction.y
        if (t < tMin || t > tMax) return null

        val x = ray.origin.x + t * ray.direction.x
        val z = ray.origin.z + t * ray.direction.z
        if (x < x0 || x > x1 || z < z0 || z > z1) return null

        return hitRecord(
            ray,
            Vector3(y = 1.0),
            material,
            t,
            (x - x0) / (x1 - x0),
            (z - z0) / (z1 - z0)
        )
    }

    override fun boundingBox(time0: Double, time1: Double) =
        Aabb(Point3(x0, k - 0.0001, z0), Point3(x1, k + 0.0001, z1))
}