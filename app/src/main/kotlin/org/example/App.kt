package org.example

class App {
    fun getLastElement(list: List<String>):String{
        if (list.isEmpty()) {
            return "List is empty"
        }
        return list[list.size - 1]
    }
    fun getPenultimateElement(list: List<String>): String{
        if (list.size < 2) {
            return "List is too short"
        }
        return list[list.size - 2]
    }
    fun isPalindrome(list: List<String>): Boolean {
        return list == list.reversed()
    }
    fun encode(list: List<String>): List<Pair<Int, String>> {
        return list.groupBy { it }.map { Pair(it.value.size, it.key) }
    }
    fun decode(list: List<Pair<Int, String>>): List<String> {
        return list.flatMap { (count, element) -> List(count) { element } }
    }
    fun isPrime(n: Int): Boolean {
        if (n % 2 == 0) {
            return false
        }
        return true
    }
    fun MDC(a: Int, b: Int): Int {
        var a = a
        var b = b
        while (b != 0) {
            val t = b
            b = a % b
            a = t
        }
        return a
    }
    fun listPrimesInRange(initial: Int, end: Int): List<Int> {
        return (initial..end).filter { isPrime(it) }
    }
}



fun main() {
    val list = listOf("R","R","A","R","R")
    val app = App()

    println(app.getLastElement(list))
    println(app.getPenultimateElement(list))
    println(app.isPalindrome(list))
    val listCodified = "AAAABBBCCDAA".split("")
    println(app.encode(listCodified))
    val listDecoded = listOf(Pair(4, "A"), Pair(3, "B"), Pair(2, "C"), Pair(1, "D"), Pair(2, "A"))
    println(app.decode(listDecoded))
    println(app.isPrime(7))
    println(app.MDC(12, 15))

}
