package io.github.cianciustyles

data class SolidColor(
    private val color: Color
): Texture() {
    constructor(red: Double, green: Double, blue: Double):
            this(Color(red, green, blue))

    override fun value(u: Double, v: Double, p: Point3): Color =
        color
}