package io.github.cianciustyles

class Box(
    material: Material,
    private val boxMin: Point3 = Point3(),
    private val boxMax: Point3 = Point3()
): Hittable() {
    private val sides = HittableList()

    init {
        sides.add(XyRect(material, boxMin.x, boxMax.x, boxMin.y, boxMax.y, boxMax.z))
        sides.add(XyRect(material, boxMin.x, boxMax.x, boxMin.y, boxMax.y, boxMin.z))

        sides.add(XzRect(material, boxMin.x, boxMax.x, boxMin.z, boxMax.z, boxMax.y))
        sides.add(XzRect(material, boxMin.x, boxMax.x, boxMin.z, boxMax.z, boxMin.y))

        sides.add(YzRect(material, boxMin.y, boxMax.y, boxMin.z, boxMax.z, boxMax.x))
        sides.add(YzRect(material, boxMin.y, boxMax.y, boxMin.z, boxMax.z, boxMin.x))
    }

    override fun hit(ray: Ray, tMin: Double, tMax: Double) =
        sides.hit(ray, tMin, tMax)

    override fun boundingBox(time0: Double, time1: Double) =
        Aabb(boxMin, boxMax)
}