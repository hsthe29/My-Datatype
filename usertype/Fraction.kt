package usertype

class Fraction(var numerator : Long = 0, denominator : Long = 1) {

    var denominator : Long = denominator
        set(value) { if(value != 0L) field = value}

    operator fun plus(other : Fraction) : Fraction {
        val gcd = gcd(denominator, other.denominator)
        val newDeno = denominator * other.denominator
        val newNume = numerator * other.denominator + denominator * other.numerator
        return Fraction(newNume / gcd, newDeno / gcd)
    }
    operator fun plusAssign(other: Fraction) {
        val gcd = gcd(denominator, other.denominator)
        numerator = numerator * other.denominator + denominator * other.numerator
        denominator *= other.denominator
        numerator /= gcd
        denominator /= gcd
    }

    operator fun minus(other : Fraction): Fraction {
        val gcd = gcd(denominator, other.denominator)
        val newDeno = denominator * other.denominator
        val newNume = numerator * other.denominator - denominator * other.numerator
        return Fraction(newNume / gcd, newDeno / gcd)
    }
    operator fun minusAssign(other: Fraction) {
        val gcd = gcd(denominator, other.denominator)
        numerator = numerator * other.denominator - denominator * other.numerator
        denominator *= other.denominator
        numerator /= gcd
        denominator /= gcd
    }

    operator fun times(other : Fraction) =
        Fraction(numerator * other.numerator, denominator * other.denominator)

    operator fun timesAssign(other: Fraction) {
        numerator *= other.numerator
        denominator *= other.denominator
    }

    operator fun div(other : Fraction) =
        Fraction(numerator * other.denominator, denominator * other.numerator)

    operator fun divAssign(other: Fraction) {
        numerator *= other.denominator
        denominator *= other.numerator
    }

    override operator fun equals(other: Any?) =
        if(other is Fraction) value() == other.value()
        else false

    operator fun compareTo(other: Fraction) : Int {
        return value().compareTo(other.value())
    }

    fun update(x : Long, y : Long) {
        if(y != 0L){
            numerator = x
            denominator = y
        } else TODO("Invalid!")
    }

    fun simplify(): Fraction{
        val gcd = gcd(numerator, denominator)
        return Fraction(numerator / gcd, denominator / gcd)
    }

    fun value() = numerator.toDouble() / denominator

    override fun toString() = "$numerator / $denominator"
}