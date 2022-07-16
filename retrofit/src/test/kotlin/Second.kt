
import io.qameta.allure.Description
import io.qameta.allure.Link
import models.Contributor
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.reflect.full.memberProperties

class Second {

    @Test
    @Link(name = "helpers", type = "Link")
    @Description("Super test")
    @Tags(Tag("REGRESS"), Tag("GOGOLOK"))
    fun test1() {
        val c = Contributor("asd", 33,55, "sdgf", "afdsvx")
//        println(c.javaClass.kotlin.memberProperties.forEach(::println))
        println(c::class.java.kotlin.memberProperties.toList().get(3).name)
        Assertions.assertTrue(true)
    }

    @Test
    @Tags(Tag("REGRESS"), Tag("OZERANSKI"))
    fun test2() {
        Assertions.assertTrue(false)
    }

    @Test
    @Tags(Tag("SMOKE"), Tag("GOGOLOK"))
    @DisplayName("TEST NAME")
    fun test3() {
        Assertions.assertTrue(false)
    }

    @ParameterizedTest
    @ValueSource(strings = arrayOf("q", "qw", "qwe", "qwer"))
    @Tags(Tag("SMOKE"), Tag("TUDESKI"))
    @Link(value = "http://Spring.com")
    @DisplayName("Test name {arg}")
    fun test4(arg: String) {

        Assertions.assertTrue(arg.length > 2)
    }

}