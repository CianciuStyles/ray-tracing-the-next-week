package io.github.cianciustyles

data class HitRecord(
    val point: Point3,
    val normal: Vector3,
    val material: Material,
    val t: Double,
    val u: Double,
    val v: Double,
    val frontFace: Boolean
)