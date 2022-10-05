package io.github.cianciustyles

import kotlin.math.sin

class NoiseTexture(
    private val scale: Double = 0.0,
    private val noise: Perlin = Perlin(),
) : Texture() {
    override fun value(u: Double, v: Double, p: Point3): Color =
        Color.WHITE * 0.5 * (1 + sin(scale * p.z + 10 * noise.turbulence(p)))
}