package oop

import collections.Product

data class OrderItem(val product: Product, val quantity: Int)

fun main() {
    val item = OrderItem(Product("baseball", 10.0), 5)
    val itemCopy = item.copy(quantity = 6)

    println(item)
    println(itemCopy)
    println(item.product == itemCopy.product)
    println(item.product === itemCopy.product)
}