package io.github.cianciustyles

import kotlin.math.abs
import kotlin.math.floor

class Perlin {
    private val random: Array<Vector3> = Array(pointCount) { Vector3.random(-1.0, 1.0).unit() }
    private val permX: IntArray = perlinGeneratePerm()
    private val permY: IntArray = perlinGeneratePerm()
    private val permZ: IntArray = perlinGeneratePerm()

    private fun noise(p: Point3): Double {
        val u = p.x - floor(p.x)
        val v = p.y - floor(p.y)
        val w = p.z - floor(p.z)

        val i = (floor(p.x)).toInt()
        val j = (floor(p.y)).toInt()
        val k = (floor(p.z)).toInt()

        val c = Array(2) { Array(2) { Array(2) { Vector3() } } }
        for (di in 0 until 2) {
            for (dj in 0 until 2) {
                for (dk in 0 until 2) {
                    c[di][dj][dk] = random[
                            permX[(i+di) and 255] xor
                            permY[(j+dj) and 255] xor
                            permZ[(k+dk) and 255]
                    ]
                }
            }
        }

        return trilinearInterpolation(c, u, v, w)
    }

    fun turbulence(p: Point3, depth: Int = 7): Double {
        var accum = 0.0
        var tempP = p
        var weight = 1.0

        for (i in 0 until depth) {
            accum += weight * noise(tempP)
            weight *= 0.5
            tempP *= 2
        }

        return abs(accum)
    }

    companion object {
        private const val pointCount: Int = 256

        private fun perlinGeneratePerm(): IntArray {
            val p = IntArray(pointCount) { i -> i }
            p.shuffle()
            return p
        }

        private fun trilinearInterpolation(c: Array<Array<Array<Vector3>>>, u: Double, v: Double, w: Double): Double {
            val uu = u * u * (3 - 2 * u)
            val vv = v * v * (3 - 2 * v)
            val ww = w * w * (3 - 2 * w)
            var accum = 0.0
            
            for (i in 0 until 2)
                for (j in 0 until 2)
                    for (k in 0 until 2)
                        accum += (i * uu + (1 - i) * (1 - uu)) *
                                 (j * vv + (1 - j) * (1 - vv)) *
                                 (k * ww + (1 - k) * (1 - ww)) *
                                 (c[i][j][k] dot Vector3(u - i, v - j, w - k))

            return accum
        }
    }
}
