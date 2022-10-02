package io.github.cianciustyles

class HittableList(
    val objects: MutableList<Hittable> = mutableListOf()
) : Hittable() {
    fun add(obj: Hittable) {
        objects.add(obj)
    }

    fun clear() {
        objects.clear()
    }

    override fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord? {
        var hitRecord: HitRecord? = null
        var closestSoFar = tMax

        objects.forEach { obj ->
            obj.hit(ray, tMin, closestSoFar)?.let {
                closestSoFar = it.t
                hitRecord = it
            }
        }

        return hitRecord
    }

    override fun boundingBox(time0: Double, time1: Double): Aabb? {
        if (objects.isEmpty()) return null

        var outputBox = Aabb()
        var firstBox = true

        objects.forEach { obj ->
            when (val tempBox = obj.boundingBox(time0, time1)) {
                null -> return null
                else -> {
                    outputBox = if (firstBox) tempBox else Aabb.surroundingBox(outputBox, tempBox)
                    firstBox = false
                }
            }
        }

        return outputBox
    }
}