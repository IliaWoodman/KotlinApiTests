package models.book_store

data class BookResource(
    val book: Book,
    val links: List<Link>
)

data class Link (
    val rel: String,
    val href: String
)

data class Book(
    val isbn: String,
    val author: String,
    val title: String,
    val synopsis: String?,
    val language: String?
)