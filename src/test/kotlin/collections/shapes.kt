package collections

abstract class Shape {
    abstract fun getArea(): Double
    abstract fun getPerimeter(): Double
}

class Circle(val radius: Double) : Shape() {
    override fun getArea() = Math.PI * radius * radius
    override fun getPerimeter(): Double = 2.0 * Math.PI * radius
}

class Square(val side: Double) : Shape() {
    override fun getArea(): Double = side * side
    override fun getPerimeter(): Double = 4 * side
}