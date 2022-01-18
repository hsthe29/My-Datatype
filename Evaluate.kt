package myDsa

import java.util.*
import kotlin.math.pow

infix fun Long.pow(b: Long): Long {
    val res = this.toDouble().pow(b.toDouble())
    if(res < Long.MAX_VALUE && res > Long.MIN_VALUE)
        return res.toLong()
    else throw ArithmeticException("Number out of range")
}

val sig = arrayOf('+', '-', '*', '/', '^')

val priority = hashMapOf('(' to 0, '+' to 1, '-' to 1, '*' to 2, '/' to 2, '^' to 3)

val opOfLong = hashMapOf('+' to {x: Long, y: Long -> x + y},
                    '-' to {x: Long, y: Long -> x - y},
                    '*' to {x: Long, y: Long -> x * y},
                    '/' to {x: Long, y: Long -> x / y},
                    '^' to {x: Long, y: Long -> x pow y})

val opOfDouble = hashMapOf('+' to {x: Double, y: Double -> x + y},
                    '-' to {x: Double, y: Double -> x - y},
                    '*' to {x: Double, y: Double -> x * y},
                    '/' to {x: Double, y: Double -> x / y},
                    '^' to {x: Double, y: Double -> x.pow(y)})

fun eval(inputString: String, doubleValue: Boolean = false): Number{
    val s = inputString.trim().replace("\\s".toRegex(), "").split("")
        .asSequence()
        .filter { it.isNotEmpty() }
        .map { it.single() }
        .toList()
    val lst = mutableListOf<Any>()
    var temp = ""

    for(i in s){
        if(i.isDigit()){
            temp += i
        }else {
            if(temp.isNotEmpty()) {
                if(doubleValue)
                    lst.add(temp.toDouble())
                else lst.add(temp.toLong())
                temp = ""
            }
            lst.add(i)
        }
    }
    if(temp.isNotEmpty())
        if(doubleValue)
            lst.add(temp.toDouble())
        else lst.add(temp.toLong())

    val st = Stack<Number>()
    val opChar = Stack<Char>()

    fun compute(sign: Char) {
        val y = st.pop()
        val x = st.pop()
        if(doubleValue){
            st.push(opOfDouble[sign]?.let { it(x.toDouble(), y.toDouble()) })
        } else st.push(opOfLong[sign]?.let { it(x.toLong(), y.toLong()) })
    }

    for(i in lst){
        if(i is Number) {
            st.push(i)
        } else {
            when(i as Char){
                in sig -> {
                    if(opChar.isEmpty())
                        opChar.push(i)
                    else {
                        if(priority[i]!! > priority[opChar.peek()]!!)
                            opChar.push(i)
                        else {
                            val sign = opChar.pop()
                            compute(sign)
                            opChar.push(i)
                        }
                    }
                }
                '(' -> opChar.push(i)
                else -> {
                    while(opChar.isNotEmpty() && opChar.peek() != '('){
                        compute(opChar.pop())
                    }
                    if(opChar.isNotEmpty())
                        opChar.pop()
                }
            }
        }
    }

    while(opChar.isNotEmpty())
        compute(opChar.pop())
    return st[0]
}

fun main(){
    println(eval("1 + 2 ^(3 * 2) - 2")) // 63
    println(eval("6 * 3 -2 / 4 ^ (3 + 1 * 4) / 5", doubleValue = true)) // 17.9993896484375
    println(eval("6 * 3 -2 / 4 ^ (3 + 1 * 4) / 5", doubleValue = false)) // 18
    println(eval("2 ^ 30")) // 1073741824
}