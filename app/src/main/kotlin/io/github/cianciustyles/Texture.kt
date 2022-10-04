package io.github.cianciustyles

abstract class Texture {
    abstract fun value(u: Double, v: Double, p: Point3): Color
}