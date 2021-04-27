package collections

abstract class Shape {
    open val area: Double = 0.0

//    abstract fun getArea(): Double
    abstract fun getPerimeter(): Double
}

class Circle(val radius: Double) : Shape() {
    override val area: Double
        get() = Math.PI * radius * radius

//    override fun getArea() = Math.PI * radius * radius
    override fun getPerimeter(): Double = 2.0 * Math.PI * radius
}

class Square(val side: Double) : Shape() {
    override val area: Double = side * side

    //    override fun getArea(): Double = side * side
    override fun getPerimeter(): Double = 4 * side
}