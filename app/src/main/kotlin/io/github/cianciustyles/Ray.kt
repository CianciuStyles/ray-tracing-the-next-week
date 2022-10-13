package io.github.cianciustyles

class Ray(
    val origin: Point3 = Point3(),
    val direction: Vector3 = Vector3(),
    val time: Double = 0.0
) {
    fun at(t: Double): Point3 =
        origin + direction * t

    fun rayColor(background: Color, world: Hittable, depth: Int): Color {
        // If we've exceeded the ray bounce limit, no more light is gathered.
        if (depth <= 0) return Color.BLACK

        val hitRecord = world.hit(this, 0.001, Double.POSITIVE_INFINITY) ?: return background

        val emitted = hitRecord.material.emitted(hitRecord.u, hitRecord.v, hitRecord.point)
        val (attenuation, scatteredRay) = hitRecord.material.scatter(this, hitRecord)
        return if (attenuation == null || scatteredRay == null)
            emitted
        else
            emitted + attenuation * scatteredRay.rayColor(background, world, depth - 1)
    }
}