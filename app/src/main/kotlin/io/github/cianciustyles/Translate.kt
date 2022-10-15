package io.github.cianciustyles

class Translate(
    private val hittable: Hittable,
    private val displacement: Vector3
): Hittable() {
    override fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord? {
        val movedRay = Ray(ray.origin - displacement, ray.direction, ray.time)
        val hitRecord = hittable.hit(movedRay, tMin, tMax) ?: return null
        return hitRecord(
            hitRecord.point + displacement,
            movedRay,
            hitRecord.normal,
            hitRecord.material,
            hitRecord.t,
            hitRecord.u,
            hitRecord.v
        )
    }

    override fun boundingBox(time0: Double, time1: Double): Aabb? {
        val boundingBox = hittable.boundingBox(time0, time1) ?: return null
        return Aabb(boundingBox.minimum + displacement, boundingBox.maximum + displacement)
    }
}