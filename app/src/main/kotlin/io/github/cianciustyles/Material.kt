package io.github.cianciustyles

abstract class Material {
    abstract fun scatter(rayIn: Ray, hitRecord: HitRecord): Pair<Color?, Ray?>

    open fun emitted(u: Double, v: Double, p: Point3): Color = Color.BLACK
}