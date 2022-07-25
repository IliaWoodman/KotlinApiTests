import clients.GitHub
import helpers.*
import io.qameta.allure.Owner
import models.github.Contributor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import tags.CommonTags.EXPERIMENTAL
import tags.TeamTags.GOGOLOK

class GitHubTests {

    @Test
    @Tag(EXPERIMENTAL)
    @DisplayName("Тестовый тест")
    @Owner(GOGOLOK)
    fun getContributors() {
        var contr: Contributor? = null
        var content: String? = null
        makeRequest(GitHub::class.java) {
            contributors("OpenFeign", "feign")
        }.andCheckHeaders("Проверяем Content-Type и vary") {
            assertEquals(get("Content-Type"), "application/json; charset=utf-8")
            assertEquals(get("vary"), "Accept, Accept-Encoding, Accept, X-Requested-With")
        }.andCheckBody("Пользователя velо") {
            assertEquals(this[0].login, "velo")
            assertEquals(this[0].contributions, 145)
        }.extractDataFromBody("Извлекаем пользователя velo") {
            contr = get(0)
        }.extractDataFromHeaders("Извлекаем Content-Type") {
            content = get("Content-Type")
        }
        println(contr?.login)
        println(content)
    }
}