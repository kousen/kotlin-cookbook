package singleton

import java.sql.Connection

object ConnectionPool {
    private val availablePool = mutableListOf<Connection>()
    val usedPool = mutableListOf<Connection>()

    init {
        availablePool
    }

    fun getConnection() {
        // remove next connection from available pool
        // add it to the used connections pool
        // return connection
    }
}