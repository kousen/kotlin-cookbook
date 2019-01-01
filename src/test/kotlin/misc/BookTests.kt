package misc

import collections.Book
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.LocalDate
import java.time.Month
import kotlin.math.exp

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
}