package io.github.cianciustyles

class YzRect(
    private val material: Material,
    private val y0: Double,
    private val y1: Double,
    private val z0: Double,
    private val z1: Double,
    private val k: Double
): Hittable() {
    override fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord? {
        val t = (k - ray.origin.x) / ray.direction.x
        if (t < tMin || t > tMax) return null

        val y = ray.origin.y + t * ray.direction.y
        val z = ray.origin.z + t * ray.direction.z
        if (y < y0 || y > y1 || z < z0 || z > z1) return null

        return hitRecord(
            ray,
            Vector3(x = 1.0),
            material,
            t,
            (y - y0) / (y1 - y0),
            (z - z0) / (z1 - z0)
        )
    }

    override fun boundingBox(time0: Double, time1: Double) =
        Aabb(Point3(k - 0.0001, y0, z0), Point3(k + 0.0001, y1, z1))
}