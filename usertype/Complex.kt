package usertype

import kotlin.math.sqrt

data class Complex(var re: Double = 0.0, var im: Double = 0.0){

    override operator fun equals(c: Any?): Boolean {
        if(c is Complex)
            return re == c.re && im == c.im
        return false
    }
    fun toVector2D() = Vector2D(re, im)
    override fun toString() = "$re ${if(im >= 0.0) '+' else ""}${im}i"
}

val Number.i: Complex
    get() = Complex(0.0, this.toDouble())

val Complex.II: Double
    get() = sqrt(re * re + im * im)

operator fun Number.plus(c: Complex) =
    Complex(this.toDouble() + c.re, c.im)

operator fun Complex.plus(c: Complex) =
    Complex(this.re + c.re, this.im + c.im)

operator fun Complex.minus(c: Complex) =
    Complex(this.re - c.re, this.im - c.im)

operator fun Complex.times(c: Complex) =
    Complex(this.re * c.re - this.im * c.im, this.re * c.im + this.im * c.re)

operator fun Number.minus(c: Complex) =
    Complex(this.toDouble() + c.re, -c.im)

operator fun Complex.plusAssign(c: Complex) {
    this.re += c.re
    this.im += c.im
}

operator fun Complex.div(c: Complex): Complex {
    val sq = c.re * c.re + c.im * c.im
    return Complex((this.re * c.re + this.im * c.im) / sq, (this.im * c.re - this.re * c.im) / sq)
}

operator fun Complex.divAssign(c: Complex) {
    val sq = c.re * c.re + c.im * c.im
    val r = this.re * c.re + this.im * c.im
    val i = this.im * c.re - this.re * c.im
    re = r / sq
    im = i / sq
}
operator fun Complex.minusAssign(c: Complex) {
    this.re -= c.re
    this.im -= c.im
}

operator fun Complex.timesAssign(c: Complex) {
    val r = this.re * c.re - this.im * c.im
    val i = this.re * c.im + this.im * c.re
    re = r
    im = i
}

fun trigonometricForm(c: Complex): String {
    val r = c.II
    val angle = c.toVector2D() from Vector2D(1.0, 0.0)
    return "$r(cos($angle) + i.sin($angle))"
}

fun Re(c: Complex) = c.re
fun Im(c: Complex) = c.im

