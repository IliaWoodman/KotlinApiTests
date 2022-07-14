package models

data class Contributor(
    val login: String,
    val contributions: Int,
    val id: Int,
    val repos: String,
    val node_id: String,
)