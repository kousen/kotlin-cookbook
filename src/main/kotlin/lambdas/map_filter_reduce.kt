package lambdas

import java.time.LocalDate
import java.time.Month
import java.time.Period

data class Person(val first: String,
                  val last: String,
                  val dob: LocalDate)

fun calculateAge(person: Person) =
        Period.between(person.dob, LocalDate.now()).years

fun mapFilterReduce() {
    val data = listOf(3, 1, 4, 1, 5, 9, 2, 6, 5)
    println(data)
    val result = data.map { it * 2 }  // [6, 2, 8, 2, 10, 18, 4, 12, 10]
            .filter { it % 3 == 0 }   // [6, 18, 12]
            .sum()                    // 36
    println("The sum of the doubles divisible by 3 is $result\n")
}

val doubler = { x: Int -> x * 2 }

val multiplier: (Int, Int) -> Int = { x, y -> x * y }

val isEven: (Int) -> Boolean = { it % 2 == 0 }

fun isOdd(i: Int) = i % 2 != 0

fun main(args: Array<String>) {
    mapFilterReduce()

    println(doubler(21))
    println(multiplier(21, 2))

    val list = listOf(3, 1, 4, 1, 5, 9)
    println(list.any(isEven))
    println(list.filter(isEven))
    println(list.filter(::isOdd))

    println((-3..3).filter { it > 0 })
    println((-3..3).any { it > 0 })
    println((-3..3).all { it > 0 })

    val avengers = listOf(
            Person("Robert", "Downey, Jr.", LocalDate.of(1965, Month.APRIL, 4)),
            Person("Chris", "Hemsworth", LocalDate.of(1983, Month.AUGUST, 11)),
            Person("Mark", "Ruffalo", LocalDate.of(1967, Month.NOVEMBER, 22)),
            Person("Chris", "Evans", LocalDate.of(1981, Month.JUNE, 13)),
            Person("Scarlett", "Johansson", LocalDate.of(1984, Month.NOVEMBER, 22)),
            Person("Jeremy", "Renner", LocalDate.of(1971, Month.JANUARY, 7)),
            Person("Chadwick", "Boseman", LocalDate.of(1977, Month.NOVEMBER, 29)),
            Person("Diana", "Rigg", LocalDate.of(1938, Month.JULY, 20)))

    avengers.map { person -> "${person.first} ${person.last} is ${calculateAge(person)} years old" }
            .forEach { println(it) }

    val averageAge = avengers.map { calculateAge(it) }
            .average()
    println("Average age: $averageAge")

    println(avengers.maxBy { it.last })
    println(avengers.maxBy(Person::last))

    println()
    avengers.groupBy { it.dob.month }.forEach { println(it) }
}