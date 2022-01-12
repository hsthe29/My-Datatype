package usertype

import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.min
import kotlin.math.sqrt

interface UVector {
    fun powTwo() : Double
    fun modulus() : Double
    override fun toString(): String
}

class Vector2D(var x: Double = 0.0, var y: Double  = 0.0) : UVector, Any() {

    operator fun plus(other : Vector2D) =
        Vector2D(x + other.x, y + other.y)

    operator fun minus(other: Vector2D) =
        Vector2D(x - other.x, y - other.y)

    operator fun times(other: Vector2D) = x * other.x + y * other.y

    operator fun rem(other: Vector2D) : Double {
        val angle = acos(times(other) / ((modulus()) * other.modulus()))
        return min(angle, PI - angle)
    }

    override operator fun equals(other: Any?): Boolean =
        if(other is Vector2D) modulus() == other.modulus()
        else false

    operator fun compareTo(other: Vector2D) : Int {
        return modulus().compareTo(other.modulus())
    }

    fun update(x : Double, y : Double) {
        this.x = x
        this.y = y
    }

    override fun powTwo() = (x * x + y * y).toDouble()

    override fun modulus() = sqrt(powTwo())

    override fun toString() = "[$x, $y]"
}

class Vector3D(var x : Double = 0.0, var y : Double = 0.0, var z : Double = 0.0) : UVector, Any() {

    operator fun plus(other : Vector3D) =
        Vector3D(x + other.x, y + other.y, z + other.z)

    operator fun minus(other: Vector3D) =
        Vector3D(x - other.x, y - other.y, z - other.z)

    operator fun times(other: Vector3D) = x * other.x + y * other.y + z * other.z

    operator fun rem(other: Vector3D) : Double {
        val angle = acos(times(other) / ((modulus()) * other.modulus()))
        return min(angle, PI - angle)
    }

    fun directionalProduct(other: Vector3D) =
        Vector3D(y * other.z - other.y * z,
            z * other.x - x * other.z, x * other.y - y * other.x)

    override operator fun equals(other: Any?): Boolean =
        if(other is Vector3D) modulus() == other.modulus()
        else false

    operator fun compareTo(other: Vector3D) : Int {
        return modulus().compareTo(other.modulus())
    }

    fun update(x : Double, y : Double) {
        this.x = x
        this.y = y
        this.z = z
    }

    override fun powTwo() = (x * x + y * y + z * z).toDouble()

    override fun modulus() = sqrt(powTwo())

    override fun toString() = "[$x, $y, $z]"

}