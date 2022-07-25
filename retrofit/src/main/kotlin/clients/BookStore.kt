package clients

import Project
import ProjectName.BOOK_STORE
import models.book_store.Book
import models.book_store.BookResource
import retrofit2.Call
import retrofit2.http.*

@Project(BOOK_STORE)
interface BookStore : BaseClient {
    @POST("books")
    fun createBook(
        @Body book: Book
    ): Call<Unit>

    @GET("books/{bookId}")
    fun getBook(@Path("bookId") bookId: String): Call<BookResource>

    @GET("books")
    fun getAllBooks(): Call<List<BookResource>>
}