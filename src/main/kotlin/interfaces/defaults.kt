package interfaces

interface Company {
    val company: String

    fun getName() = company
}

interface Employee {
    val first: String
    val last: String

    fun getName() = "$first $last"
}

class CompanyEmployee(
    override val first: String,
    override val last: String,
    override val company: String
) : Company, Employee {

    override fun getName(): String =
        "${super<Employee>.getName()} working for ${super<Company>.getName()}"
}