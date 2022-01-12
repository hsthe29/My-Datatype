package usertype

fun gcd(_a : Long, _b : Long) : Long {
    var c : Long
    var a = _a
    var b = _b
    while(b != 0L) {
        c = b
        b = a % b
        a = c
    }
    return a
}