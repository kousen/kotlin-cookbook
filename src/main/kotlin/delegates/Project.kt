package delegates

data class Project(val map: MutableMap<String, Any?>) {
    val name: String by map
    var priority: Int by map
    var completed: Boolean by map
}
