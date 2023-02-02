package datetime

import kotlinx.datetime.*

fun main() {
    val groundHogDay = LocalDate(2023, Month.FEBRUARY, 2)
    val firstDaySpring = LocalDate(2023, Month.MARCH, 20)
    val days = groundHogDay.daysUntil(firstDaySpring)
    val weeks = groundHogDay.until(
        firstDaySpring, DateTimeUnit.WEEK)

    println("There are $days from GroundHog Day until the first day of Spring")
    println("That is $weeks weeks and ${days - 7 * weeks} days")
}