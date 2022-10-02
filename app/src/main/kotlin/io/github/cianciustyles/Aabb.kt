package io.github.cianciustyles

import kotlin.math.max
import kotlin.math.min

data class Aabb(
    val minimum: Point3 = Point3(),
    val maximum: Point3 = Point3()
) {
    fun hit(r: Ray, tMin: Double, tMax: Double): Boolean {
        for (a in 0..2) {
            val invD: Double = 1.0 / r.direction[a]
            var t0 = (minimum[a] - r.origin[a]) * invD
            var t1 = (maximum[a] - r.origin[a]) * invD
            if (invD < 0.0) {
                val temp = t0
                t0 = t1
                t1 = temp
            }

            if (min(t1, tMax) <= max(t0, tMin)) return false
        }

        return true
    }

    companion object {
        fun surroundingBox(box0: Aabb, box1: Aabb): Aabb {
            val small = Point3(
                min(box0.minimum.x, box1.minimum.x),
                min(box0.minimum.y, box1.minimum.y),
                min(box0.minimum.z, box1.minimum.z)
            )
            val big = Point3(
                max(box0.maximum.x, box1.maximum.x),
                max(box0.maximum.y, box1.maximum.y),
                max(box0.maximum.z, box1.maximum.z),
            )

            return Aabb(small, big)
        }
    }
}