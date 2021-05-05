package delegates

import kotlin.properties.Delegates

val ultimateAnswer: Int by lazy {
    println("computing the answer")
    42
}

var watched: Int by Delegates.observable(1) { prop, old, new ->
    println("${prop.name} changed from $old to $new")
}

var checked: Int by Delegates.vetoable(0) { prop, old, new ->
    println("Trying to change ${prop.name} from $old to $new")
    new >= 0
}

var shouldNotBeNull: String by Delegates.notNull()


fun main() {
    println(ultimateAnswer)
    println(ultimateAnswer)

    watched *= 2
    watched *= 2

    checked = 42
    println(checked)
    checked = -1
    println(checked)
    checked = 17
    println(checked)
}