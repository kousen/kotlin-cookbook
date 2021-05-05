package oop

data class Car(val horsePower: Int)

object CarFactory {
    val cars = mutableListOf<Car>()

    fun makeCar(horsePower: Int) : Car {
        val car = Car(horsePower)
        cars.add(car)
        return car
    }

    // SE == Single Expression implementation
    fun makeCarSE(horsePower: Int) =
        Car(horsePower).apply { cars.add(this) }
}

fun main() {
    CarFactory.makeCar(150)
    CarFactory.makeCarSE(150)
    println(CarFactory.cars.size)
}