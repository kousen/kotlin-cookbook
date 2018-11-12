package collections

import java.text.NumberFormat

abstract class Employee(val id: Int, val name: String) {
    abstract fun computePay(): Double

    override fun toString(): String =
        "$name makes ${NumberFormat.getCurrencyInstance().format(computePay())}"
}

class Salaried(
    id: Int,
    name: String,
    var salary: Double
) : Employee(id, name) {

    override fun computePay(): Double = salary / 52
}

class Hourly(
    id: Int,
    name: String,
    var rate: Double,
    var hours: Double = 40.0
) : Employee(id, name) {
    override fun computePay(): Double = rate * hours
}

fun main(args: Array<String>) {
    val fred = Hourly(1, "Fred", 25.0)
    val barney = Hourly(2, "Barney", 30.0)
    val wilma = Salaried(3, "Wilma", 75000.0)
    val betty = Salaried(id = 4, name = "Betty", salary = 60000.0)

    val employees = listOf(fred, barney, wilma, betty)
    employees.forEach { println(it) }
}




