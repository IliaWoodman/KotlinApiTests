import clients.GitHub
import helpers.build
import helpers.makeRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test


class First {
    @Test
    @Tag("SMOKE1")
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
    fun test2() {
        val resp = build(GitHub::class.java).contributors("OpenFeign", "feign").execute()
        println(resp.body())
        val r = resp.body() ?: throw IllegalArgumentException()
        val user = r.find { it.login == "velo" } ?: throw NullPointerException()
        Assertions.assertTrue(user.contributions == 139)
        Assertions.assertTrue(resp.code() == 400)
    }

    @Test
    fun test3() {
        val resp = build(GitHub::class.java).contributors("OpenFeign", "feign").execute()
        val r = resp.body() ?: throw IllegalArgumentException()
        val user = r.find { it.login == "velo" } ?: throw NullPointerException()
        Assertions.assertTrue(user.contributions == 139)

        Assertions.assertTrue(resp.code() == System.getProperty("code").toInt())
    }
}



