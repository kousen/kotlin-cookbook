package misc

import collections.Book
import collections.createBook
import collections.createMultiAuthorBook
import org.apache.commons.validator.routines.ISBNValidator
import org.apache.commons.validator.routines.checkdigit.ISBNCheckDigit
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.function.Executable
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.aggregator.AggregateWith
import org.junit.jupiter.params.aggregator.ArgumentsAccessor
import org.junit.jupiter.params.aggregator.ArgumentsAggregator
import org.junit.jupiter.params.provider.CsvFileSource
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDate
import java.time.Month
import kotlin.math.exp

@Suppress("UNUSED_VARIABLE")
internal class BookTests {
    private val books = arrayOf(
        Book("1935182943", "Making Java Groovy", "Ken Kousen", LocalDate.parse("2013-09-30")),
        Book("1491947020", "Gradle Recipes for Android", "Ken Kousen", LocalDate.parse("2016-06-17")),
        Book("149197317X", "Modern Java Recipes", "Ken Kousen", LocalDate.parse("2017-08-26"))
    )

    @Test
    internal fun `test book the hard way`() {
        val book = books[0]
        assertThat(book.isbn, `is`("1935182943"))
        assertThat(book.title, `is`("Making Java Groovy"))
        assertThat(book.author, `is`("Ken Kousen"))
        assertThat(book.published, `is`(LocalDate.of(2013, Month.SEPTEMBER, 30)))
    }

    @Test
    internal fun `use JUnit 5 assertAll`() {
        val book = books[0]
        assertAll("check all properties of a book",
            { assertThat(book.isbn, `is`("1935182943")) },
            { assertThat(book.title, `is`("Making Java Groovy")) },
            { assertThat(book.author, `is`("Ken Kousen")) },
            { assertThat(book.published, `is`(LocalDate.of(2013, Month.SEPTEMBER, 30))) })
    }

    @Test
    internal fun `use data class`() {
        val book = books[0]
        val expected = Book(isbn = "1935182943", title = "Making Java Groovy",
            author = "Ken Kousen", published = LocalDate.of(2013, Month.SEPTEMBER, 30))
        assertThat(book, `is`(expected))
    }

    @Test
    internal fun `check all elements in list`() {
        val badBook = books[2].copy(title = "Modern Java Cookbook")
        val found = arrayOf(books[2], books[0], books[1]) // Add badBook to see the assertion fail
        val expected = books
        assertThat(found, arrayContainingInAnyOrder(*expected))
    }

    @Test
    internal fun `ISBNs are valid and check digit calculation works`() {
        val validator = ISBNValidator.getInstance()

        assertAll(
            { assertTrue(validator.isValid("978-0-306-40615-7")) },
            { assertTrue(validator.isValid("1935182943")) },
            { assertTrue(validator.isValid("149197317X")) },
            { assertThat(ISBNCheckDigit.ISBN13_CHECK_DIGIT.calculate("978030640615"), `is`("7")) },
            { assertThat(ISBNCheckDigit.ISBN10_CHECK_DIGIT.calculate("193518294"), `is`("3")) },
            { assertThat(ISBNCheckDigit.ISBN10_CHECK_DIGIT.calculate("149197317"), `is`("X"))}
        )
    }

    @Test
    internal fun `test books using factory function`() {
        val mjr = createBook()
        val mjg = createBook(isbn = "1935182943", title = "Making Java Groovy",
            published = LocalDate.parse("2013-09-30"))

        val kotlin_in_action = createMultiAuthorBook()

        assertThat(mjr, `is`(books[2]))
        assertThat(mjg, `is`(books[0]))
    }

    @ParameterizedTest
    @CsvSource(
        "1935182943, Making Java Groovy,         Ken Kousen, 2013-09-30",
        "1491947020, Gradle Recipes for Android, Ken Kousen, 2016-06-17",
        "149197317X, Modern Java Recipes,        Ken Kousen, 2017-08-26"
    )
    fun `books are valid`(isbn: String, title: String, author: String, date: LocalDate) {
        val now = LocalDate.now()
        val jan2013 = LocalDate.of(2013, Month.JANUARY, 1)

        assertAll(
            { assertTrue(ISBNValidator.getInstance().isValid(isbn)) },
            { assertTrue(title.isNotBlank()) },
            { assertTrue(author.isNotBlank()) },
            { assertTrue(date.isAfter(jan2013) && date.isBefore(now)) }
        )
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/book_data.csv"], numLinesToSkip = 1)
    fun `books from file are valid`(isbn: String, title: String,
                                    author: String, date: LocalDate) {
        val now = LocalDate.now()
        val jan2013 = LocalDate.of(2013, Month.JANUARY, 1)

        assertAll(
            { assertTrue(ISBNValidator.getInstance().isValid(isbn)) },
            { assertTrue(title.isNotBlank()) },
            { assertTrue(author.isNotBlank()) },
            { assertTrue(date.isAfter(jan2013) && date.isBefore(now)) }
        )
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/book_data.csv"], numLinesToSkip = 1)
    fun `books from aggregator are valid`(@AggregateWith(BookAggregator::class) book: Book) {
        val now = LocalDate.now()
        val jan2013 = LocalDate.of(2013, Month.JANUARY, 1)

        assertAll(
            { assertTrue(ISBNValidator.getInstance().isValid(book.isbn)) },
            { assertTrue(book.title::isNotBlank) },
            { assertTrue(book.author::isNotBlank) },
            { assertTrue(book.published.isAfter(jan2013) && book.published.isBefore(now)) }
        )
    }

    @TestFactory
    fun `generate and run tests from collection`(): Collection<DynamicTest> {
        return books.map { book ->
            DynamicTest.dynamicTest("Check ${book.title} is valid") {
                assertTrue(book.published >= LocalDate.of(2013, Month.JANUARY, 1))
                assertTrue(book.title::isNotBlank)
                assertTrue(book.author::isNotBlank)
            }
        }.toList()
    }
}