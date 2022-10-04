package io.github.cianciustyles

class Lambertian(
    private val albedo: Texture
) : Material() {
    constructor(color: Color = Color.BLACK):
            this(SolidColor(color))
    override fun scatter(rayIn: Ray, hitRecord: HitRecord): Pair<Color, Ray> {
        var scatterDirection = hitRecord.normal + Vector3.randomUnitVector()

        // Catch degenerate scatter direction
        if (scatterDirection.nearZero()) scatterDirection = hitRecord.normal

        return Pair(
            albedo.value(hitRecord.u, hitRecord.v, hitRecord.point),
            Ray(hitRecord.point, scatterDirection, rayIn.time)
        )
    }
}