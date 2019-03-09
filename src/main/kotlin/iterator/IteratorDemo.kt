package iterator

data class Player(val name: String)

class Team(val name: String,
           val players: MutableList<Player> = mutableListOf()) : Iterable<Player> {
    override fun iterator(): Iterator<Player> = players.iterator()

    fun addPlayers(vararg people: Player) = players.addAll(people)
    fun removePlayer(player: Player) {
        if (players.contains(player)) {
            players.remove(player)
        }
    }
}

//operator fun Team.iterator() : Iterator<Player> = players.iterator()

fun main() {
    val team = Team("Warriors")
    team.addPlayers(
        Player("Curry"),
        Player("Thompson"),
        Player("Durant"),
        Player("Green"),
        Player("Cousins")
    )

    for (player in team) {
        println(player)
    }

    println(team.any { it.name == "Curry" })
    println(team.map { it.name }.sorted().joinToString())
}
