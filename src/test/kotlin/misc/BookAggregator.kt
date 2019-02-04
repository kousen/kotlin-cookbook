package misc

import collections.Book
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.params.aggregator.ArgumentsAccessor
import org.junit.jupiter.params.aggregator.ArgumentsAggregator
import java.time.LocalDate

class BookAggregator : ArgumentsAggregator {
    override fun aggregateArguments(accessor: ArgumentsAccessor, context: ParameterContext) =
        Book(
            accessor[0].toString(),
            accessor[1].toString(),
            accessor[2].toString(),
            accessor.get(3, LocalDate::class.java)
        )
}