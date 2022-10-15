package io.github.cianciustyles

import kotlin.math.ln
import kotlin.random.Random

class ConstantMedium private constructor(
    val boundary: Hittable,
    val negativeInverseDensity: Double,
    val phaseFunction: Isotropic
): Hittable() {

    constructor(boundary: Hittable, density: Double, texture: Texture):
            this(boundary, -1.0 / density, Isotropic(texture))

    constructor(boundary: Hittable, density: Double, color: Color):
            this(boundary, -1.0 / density, Isotropic(color))

    override fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord? {
        // Print occasional samples when debugging. To enable, set enableDebug true.
        val enableDebug = false
        val debugging = enableDebug && Random.nextDouble() < 0.00001

        var hitRecord1 = boundary.hit(ray, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)
            ?: return null
        var hitRecord2 = boundary.hit(ray, hitRecord1.t + 0.0001, Double.POSITIVE_INFINITY)
            ?: return null

        if (debugging) System.err.println("\ttMin=${hitRecord1.t}, tMax=${hitRecord2.t}\n")

        if (hitRecord1.t < tMin) hitRecord1 = hitRecord1.copy(t = tMin)
        if (hitRecord2.t > tMax) hitRecord2 = hitRecord2.copy(t = tMax)
        if (hitRecord1.t >= hitRecord2.t) return null
        if (hitRecord1.t < 0.0) hitRecord1 = hitRecord1.copy(t = 0.0)

        val rayLength = ray.direction.length()
        val distanceInsideBoundary = (hitRecord2.t - hitRecord1.t) * rayLength
        val hitDistance = negativeInverseDensity * ln(Random.nextDouble())

        if (hitDistance > distanceInsideBoundary) return null

        val t = hitRecord1.t + hitDistance / rayLength
        val hitRecord = HitRecord(
            ray.at(t),
            Vector3(x = 1.0),  // arbitrary
            phaseFunction,
            t,
            0.0,
            0.0,
            true // arbitrary
        )

        if (debugging) {
            System.err.println("hitDistance = $hitDistance\n")
            System.err.println("hitRecord.t = ${hitRecord.t}")
            System.err.println("hitRecord.point = ${hitRecord.point}\n")
        }

        return hitRecord
    }

    override fun boundingBox(time0: Double, time1: Double) =
        boundary.boundingBox(time0, time1)
}