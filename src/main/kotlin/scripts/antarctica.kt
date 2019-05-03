package scripts

import java.time.Instant
import java.time.ZoneId

fun main() {

    val regex = """.*Antarctica.*""".toRegex()
    val instant = Instant.now()

    val zones = ZoneId.getAvailableZoneIds()
        .filter { regex.matches(it) }
        .map { instant.atZone(ZoneId.of(it)) }
        .sortedBy { it.offset.totalSeconds }
        .toList()

    zones.forEach { zdt ->
        println(String.format("%7s: %25s %7s", zdt.offset, zdt.zone,
            zdt.zone.rules.isDaylightSavings(instant)))
    }

    println()
    val southPole = instant.atZone(ZoneId.of("Antarctica/South_Pole"))
    val dst = southPole.zone.rules.isDaylightSavings(Instant.now())
    println("It is ${southPole.toLocalTime()} (UTC${southPole.offset}) at the South Pole")
    println("The South Pole ${if (dst) "is" else "is not"} currently on Daylight Savings Time")
}

