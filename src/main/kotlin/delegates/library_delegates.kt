package delegates

import kotlin.properties.Delegates

class UseDelegates {
    val ultimateAnswer: Int by lazy {
        println("computing the answer")
        42
    }

    var watched: Int by Delegates.observable(1) { prop, old, new ->
        println("${prop.name} changed from $old to $new")
    }

    var checked: Int by Delegates.vetoable(1) { prop, old, new ->
        println("Trying to change ${prop.name} from $old to $new")
        new >= 0
    }
}

fun main() {
    val demo = UseDelegates()
    println(demo.ultimateAnswer)
    println(demo.ultimateAnswer)

    demo.watched *= 2
    demo.watched *= 2

    demo.checked = 42
    println(demo.checked)
    demo.checked = -1
    println(demo.checked)
    demo.checked = 17
    println(demo.checked)
}