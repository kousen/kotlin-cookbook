package singleton

import java.sql.Connection

object MySingleton {
    const val myProperty = 3

    fun myFunction() = "Hello"
}

object ConnectionPool {
    private val availablePool = mutableListOf<Connection>()
    val usedPool = mutableListOf<Connection>()

    fun getConnection() {
        // remove next connection from available pool
        // add it to the used connections pool
        // return connection
    }
}