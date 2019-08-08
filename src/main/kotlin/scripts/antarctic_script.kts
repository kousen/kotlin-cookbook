package scripts

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

val regex = """.*Antarctica.*""".toRegex()
val instant: Instant = Instant.now()

ZoneId.getAvailableZoneIds()
    .filter { regex.matches(it) }
    .map { instant.atZone(ZoneId.of(it)) }
    .sortedBy { it.offset.totalSeconds }
    .forEach { zdt ->
        println(String.format("%7s: %25s %7s",
            zdt.offset, zdt.zone, zdt.zone.rules.isDaylightSavings(instant)))
    }

println()
val southPole: ZonedDateTime = instant.atZone(ZoneId.of("Antarctica/South_Pole"))
val dst = southPole.zone.rules.isDaylightSavings(Instant.now())
println("It is currently ${southPole.toLocalTime()} (UTC${southPole.offset}) at the South Pole")
println("The South Pole ${if (dst) "is" else "is not"} on Daylight Savings Time")

