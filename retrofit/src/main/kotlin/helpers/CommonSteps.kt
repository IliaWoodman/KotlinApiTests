package helpers

import io.qameta.allure.Allure
import okhttp3.Headers
import retrofit2.Response


fun <R> Response<R>.andCheckHeaders(description: String = "", block: Headers.() -> Unit): Response<R> {
    Allure.step("User check headers. $description")
    headers().block()
    return this
}

fun <R> Response<R>.andCheckBody(description: String = "", block: R.() -> Unit): Response<R> {
    Allure.step("User check body. $description")
    body()?.block()
    return this
}

fun <R> Response<R>.extractDataFromBody(description: String = "", block: R.() -> Unit): Response<R> {
    Allure.step("User extract data from body. $description")
    body()?.block()
    return this
}

fun <R> Response<R>.extractDataFromHeaders(description: String = "", block: Headers.() -> Unit): Response<R> {
    Allure.step("User extract data from headers. $description")
    headers().block()
    return this
}