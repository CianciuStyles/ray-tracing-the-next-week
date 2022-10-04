package io.github.cianciustyles

import kotlin.math.sin

class CheckerTexture(
    val even: Texture,
    val odd: Texture
): Texture() {
    constructor(c1: Color, c2: Color):
            this(SolidColor(c1), SolidColor(c2))

    override fun value(u: Double, v: Double, p: Point3): Color {
        val sines = sin(10 * p.x) * sin(10 * p.y) *  sin(10 * p.z)
        return if (sines < 0) {
            odd.value(u, v, p)
        } else {
            even.value(u, v, p)
        }
    }
}