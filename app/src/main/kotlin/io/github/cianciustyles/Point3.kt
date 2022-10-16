package io.github.cianciustyles

import kotlin.random.Random

data class Point3(
    val x: Double = 0.0,
    val y: Double = 0.0,
    val z: Double = 0.0
) {
    operator fun plus(other: Vector3): Point3 =
        Point3(
            x + other.x,
            y + other.y,
            z + other.z
        )

    operator fun minus(vector: Vector3): Point3 =
        plus(-vector)

    operator fun minus(point: Point3): Vector3 =
        Vector3(
            x - point.x,
            y - point.y,
            z - point.z
        )

    operator fun times(i: Int): Point3 =
        Point3(x * i, y * i, z * i)

    operator fun times(i: Double): Point3 =
        Point3(x * i, y * i, z * i)

    operator fun get(index: Int): Double =
        when (index) {
            0 -> this.x
            1 -> this.y
            2 -> this.z
            else -> throw IllegalArgumentException("Invalid index")
        }

    companion object {
        fun random(min: Double, max: Double) =
            Point3(
                Random.nextDouble(min, max),
                Random.nextDouble(min, max),
                Random.nextDouble(min, max)
            )
    }
}