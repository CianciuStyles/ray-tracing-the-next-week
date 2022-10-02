package io.github.cianciustyles

import kotlin.random.Random

class BvhNode: Hittable {
    private val left: Hittable
    private val right: Hittable
    private val box: Aabb

    constructor(objects: List<Hittable>, start: Int, end: Int, time0: Double, time1: Double) {
        val comparator = BoxComparator(Random.nextInt(2))

        when (val objectSpan = end - start) {
            1 -> {
                left = objects[start]
                right = objects[start]
            }
            2 -> {
                if (comparator.compare(objects[start], objects[start + 1]) < 0) {
                    left = objects[start]
                    right = objects[start + 1]
                } else {
                    left = objects[start + 1]
                    right = objects[start]
                }
            }
            else -> {
                val sortedObjects = objects.sortedWith(comparator)
                val mid = start + objectSpan / 2
                left = BvhNode(sortedObjects, start, mid, time0, time1)
                right = BvhNode(sortedObjects, mid, end, time0, time1)
            }
        }

        val boxLeft = left.boundingBox(time0, time1)
        val boxRight = right.boundingBox(time0, time1)
        if (boxLeft == null || boxRight == null)
            System.err.println("No bounding box in BvhNode constructor.\n")

        box = Aabb.surroundingBox(boxLeft ?: Aabb(), boxRight ?: Aabb())
    }

    constructor(hittableList: HittableList, time0: Double, time1: Double):
            this(hittableList.objects, 0, hittableList.objects.size, time0, time1)

    class BoxComparator(private val axis: Int): Comparator<Hittable> {
        override fun compare(a: Hittable?, b: Hittable?): Int {
            val boxA = a?.boundingBox(0.0, 0.0)
            val boxB = b?.boundingBox(0.0, 0.0)
            if (boxA == null || boxB == null)
                System.err.println("No bounding box in BvhNode constructor.\n")

            return ((boxA ?: Aabb()).minimum[axis] compareTo (boxB ?: Aabb()).minimum[axis])
        }
    }

    override fun boundingBox(time0: Double, time1: Double): Aabb = box

    override fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord? {
        if (!box.hit(ray, tMin, tMax)) return null
        return left.hit(ray, tMin, tMax) ?: right.hit(ray, tMin, tMax)
    }
}