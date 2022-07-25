import clients.BookStore
import helpers.andCheckBody
import helpers.andCheckCode
import helpers.makeRequest
import io.qameta.allure.Owner
import models.book_store.Book
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import tags.CommonTags.REGRESS
import tags.TeamTags.OZERANSKI

class BookStoreTests {

    @Test
    @DisplayName("Добавляем и получаем книгу")
    @Owner(OZERANSKI)
    @Tag(REGRESS)
    fun addNewBookAndCheck() {
        val newBook = Book("98765", "Ivan Ivanov", "Book#1", "sinop", "RU")
        makeRequest(BookStore::class.java) {
            createBook(newBook)
        }.andCheckCode(200)

       val a = makeRequest(BookStore::class.java) {
            getBook(newBook.isbn)
        }.andCheckCode(200).andCheckBody {
            assertEquals(book.author, newBook.author)
            assertEquals(book.title, newBook.title)
            assertEquals(book.language, newBook.language)
            assertEquals(book.synopsis, newBook.synopsis)
        }
    }

    @Test
    @DisplayName("Получаемм список книг")
    @Owner(OZERANSKI)
    @Tag(REGRESS)
    fun getAllBooks() {
        makeRequest(BookStore::class.java) {
            getAllBooks()
        }.andCheckBody {
            val book = this.first { it.book.title == "Animal Farm" }
            assertEquals(book.book.author, "George Orwell")
        }
    }
}