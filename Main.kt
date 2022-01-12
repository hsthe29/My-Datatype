import usertype.Fraction
import usertype.Vector2D
import usertype.Vector3D

fun main() {
    val frac1 = Fraction(120, 50)
    val frac2 = Fraction(12, 60)
    println(frac1 + frac2) // 780 / 300
    println((frac1 + frac2).simplify()) // 13 / 5
    println((frac1 + frac2).value()) // 2.6
    println((frac1 * frac2).simplify()) // 12 / 15
    frac1.update(5, 8)
    frac2.update(1, 4)
    println(frac1 + frac2) // 7 / 8

    val lst = MutableList(10) {Vector2D(it.toDouble(), it.toDouble() * 3)}
    // [[0.0, 0.0], [1.0, 3.0], [2.0, 6.0], [3.0, 9.0], [4.0, 12.0], [5.0, 15.0], [6.0, 18.0], [7.0, 21.0], [8.0, 24.0], [9.0, 27.0]]
    println(lst)
    println(lst[0] + lst[1]) // [1.0, 3.0]

    println("angle between two vectors : ${lst[4] % lst[2]} rad") // angle between two vectors : 2.1073424255447017E-8 rad
    println(lst[3] * lst[5]) // 150.0
    val vec3d1 = Vector3D(1.0, 2.0, 3.0)
    val vec3d2 = Vector3D(5.0, 1.0, 2.0)
    println(vec3d1.directionalProduct(vec3d2)) // [1.0, 13.0, -9.0]

}