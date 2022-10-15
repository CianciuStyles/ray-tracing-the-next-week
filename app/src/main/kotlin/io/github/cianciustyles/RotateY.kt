package io.github.cianciustyles

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class RotateY(
    private val hittable: Hittable,
    angle: Double
): Hittable() {
    private val sinTheta: Double
    private val cosTheta: Double
    private val boundingBox: Aabb?

    init {
        val radians = angle / 180.0 * PI
        sinTheta = sin(radians)
        cosTheta = cos(radians)
        val bBox = hittable.boundingBox(0.0, 1.0)
        boundingBox = if (bBox == null) {
            null
        } else {
            val minValues = arrayOf(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
            val maxValues = arrayOf(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY)

            for (i in 0 until 2) {
                for (j in 0 until 2) {
                    for (k in 0 until 2) {
                        val x = i * bBox.maximum.x + (1 - i) * bBox.minimum.x
                        val y = j * bBox.maximum.y + (1 - j) * bBox.minimum.y
                        val z = k * bBox.maximum.z + (1 - k) * bBox.minimum.z

                        val newX = cosTheta * x + sinTheta * z
                        val newZ = -sinTheta * x + cosTheta * z

                        val tester = Vector3(newX, y, newZ)

                        for (c in 0..2) {
                            minValues[c] = minValues[c].coerceAtMost(tester[c])
                            maxValues[c] = maxValues[c].coerceAtLeast(tester[c])
                        }
                    }
                }
            }

            Aabb(
                Point3(minValues[0], minValues[1], minValues[2]),
                Point3(maxValues[0], maxValues[1], maxValues[2])
            )
        }
    }

    override fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord? {
        val origin = ray.origin.copy(
            x = cosTheta * ray.origin.x - sinTheta * ray.origin.z,
            z = sinTheta * ray.origin.x + cosTheta * ray.origin.z
        )
        val direction = ray.direction.copy(
            x = cosTheta * ray.direction.x - sinTheta * ray.direction.z,
            z = sinTheta * ray.direction.x + cosTheta * ray.direction.z
        )

        val rotatedRay = Ray(origin, direction, ray.time)
        val hitRecord = hittable.hit(rotatedRay, tMin, tMax) ?: return null

        return hitRecord(
            hitRecord.point.copy(
                x =  cosTheta * hitRecord.point.x + sinTheta * hitRecord.point.z,
                z = -sinTheta * hitRecord.point.x + cosTheta * hitRecord.point.z
            ),
            rotatedRay,
            hitRecord.normal.copy(
                x =  cosTheta * hitRecord.normal.x + sinTheta * hitRecord.normal.z,
                z = -sinTheta * hitRecord.normal.x + cosTheta * hitRecord.normal.z
            ),
            hitRecord.material,
            hitRecord.t,
            hitRecord.u,
            hitRecord.v
        )
    }

    override fun boundingBox(time0: Double, time1: Double) = boundingBox
}