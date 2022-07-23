import clients.GitHub
import helpers.*
import io.qameta.allure.Owner
import models.Contributor
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import tags.CommonTags.EXPERIMENTAL
import tags.TeamTags.GOGOLOK
import tags.TeamTags.OZERANSKI
import tags.TeamTags.TUDESKI

class First {
    @Test
    @Tag("SMOKE1")
    @Owner(OZERANSKI)
    fun test1() {
        val resp = makeRequest(GitHub::class.java) {
            contributors("OpenFeign", "feign")
        }
        resp.body()?.let {
            it.get(0).login == "velo"
        }

        resp.body()?.let {
            it.get(0).login == "velo"
        }
        println(resp.body()?.get(0)?.login)
    }

    @Test
    @Tag(EXPERIMENTAL)
    @DisplayName("Тестовый тест")
    @Owner(TUDESKI)
    fun test11() {
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

    @Test
    @Owner(TUDESKI)
    fun test2() {
        val resp = build(GitHub::class.java).contributors("OpenFeign", "feign").execute()
        println(resp.body())
        val r = resp.body() ?: throw IllegalArgumentException()
        val user = r.find { it.login == "velo" } ?: throw NullPointerException()
        Assertions.assertTrue(user.contributions == 139)
        Assertions.assertTrue(resp.code() == 400)
    }

    @Test
    @Owner(GOGOLOK)
    fun test3() {
        val resp = build(GitHub::class.java).contributors("OpenFeign", "feign").execute()
        val r = resp.body() ?: throw IllegalArgumentException()
        val user = r.find { it.login == "velo" } ?: throw NullPointerException()
        Assertions.assertTrue(user.contributions == 139)
    }
}