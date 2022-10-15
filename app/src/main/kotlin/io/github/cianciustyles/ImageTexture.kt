package io.github.cianciustyles

import java.awt.image.BufferedImage
import java.io.IOException
import java.io.InputStream
import javax.imageio.ImageIO

class ImageTexture(
    filename: String
): Texture() {
    private var image: BufferedImage? = null

    init {
        val inputStream: InputStream? = javaClass.classLoader.getResourceAsStream(filename)
        try {
            image = ImageIO.read(inputStream)
        } catch (e: IOException) {
            System.err.println(e)
        }
    }

    override fun value(u: Double, v: Double, p: Point3): Color {
        // If we have no texture data, then return solid cyan as a debugging aid.
        if (image == null) return Color(0.0, 1.0, 1.0)

        // Clamp input texture coordinates to [0,1] x [1,0]
        val u1 = u.coerceIn(0.0, 1.0)
        val v1 = 1.0 - v.coerceIn(0.0, 1.0)  // Flip V to image coordinates

        var i: Int = (u1 * image!!.width).toInt()
        var j: Int = (v1 * image!!.height).toInt()

        // Clamp integer mapping, since actual coordinates should be less than 1.0
        if (i >= image!!.width)  i = image!!.width - 1
        if (j >= image!!.height) j = image!!.height - 1

        val colorScale = 1.0 / 255.0
        val pixel = image!!.getRGB(i, j)

        return Color(
            r = ((pixel shr 16) and 0xff).toDouble(),
            g = ((pixel shr 8) and 0xff).toDouble(),
            b = (pixel and 0xff).toDouble()
        ) * colorScale
    }
}