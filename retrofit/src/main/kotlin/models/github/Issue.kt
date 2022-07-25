package models.github

class Issue(
    val title: String,
    val body: String,
    val assignees: List<String>,
    val milestone: Int,
    val labels: List<String>
)