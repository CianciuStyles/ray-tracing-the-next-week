package io.github.cianciustyles

class Isotropic(
    val albedo: Texture
): Material() {
    constructor(color: Color): this(SolidColor(color))

    override fun scatter(rayIn: Ray, hitRecord: HitRecord): Pair<Color?, Ray?> {
        val attenuation = albedo.value(hitRecord.u, hitRecord.v, hitRecord.point)
        val scattered = Ray(hitRecord.point, Vector3.randomUnitVector(), rayIn.time)
        return attenuation to scattered
    }
}
