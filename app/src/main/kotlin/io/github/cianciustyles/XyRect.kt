package io.github.cianciustyles

class XyRect(
    private val material: Material,
    private val x0: Double = 0.0,
    private val x1: Double = 0.0,
    private val y0: Double = 0.0,
    private val y1: Double = 0.0,
    private val k: Double = 0.0
): Hittable() {
    override fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord? {
        val t: Double = (k - ray.origin.z) / ray.direction.z
        if (t < tMin || t > tMax) return null

        val x: Double = ray.origin.x + t * ray.direction.x
        val y: Double = ray.origin.y + t * ray.direction.y
        if (x < x0 || x > x1 || y < y0 || y > y1) return null

        return hitRecord(
            ray,
            Vector3(z = 1.0),
            material,
            t,
            (x - x0) / (x1 - x0),
            (y - y0) / (y1 - y0)
        )
    }

    override fun boundingBox(time0: Double, time1: Double) =
        Aabb(Point3(x0, y0, k - 0.0001), Point3(x1, y1, k + 0.0001))
}