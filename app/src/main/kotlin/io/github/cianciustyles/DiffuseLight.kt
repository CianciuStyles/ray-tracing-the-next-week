package io.github.cianciustyles

class DiffuseLight(private val emit: Texture): Material() {
    constructor(c: Color): this(SolidColor(c))

    override fun scatter(rayIn: Ray, hitRecord: HitRecord): Pair<Color?, Ray?> =
        null to null

    override fun emitted(u: Double, v: Double, p: Point3): Color =
        emit.value(u, v, p)
}