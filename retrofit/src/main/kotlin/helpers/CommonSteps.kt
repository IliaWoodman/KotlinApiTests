package helpers

import io.qameta.allure.Step
import okhttp3.Headers
import retrofit2.Response


@Step("User check headers. {description}")
fun <R> Response<R>.andCheckHeaders(description: String = "", block: Headers.() -> Unit): Response<R> {
    headers().block()
    return this
}
@Step("User check body. {description}")
fun <R> Response<R>.andCheckBody(description: String = "", block: R.() -> Unit): Response<R> {
    body()?.block()
    return this
}
@Step("User extract data from body. {description}")
fun <R> Response<R>.extractDataFromBody(description: String = "", block: R.() -> Unit): Response<R> {
    body()?.block()
    return this
}
@Step("User extract data from headers. {description}")
fun <R> Response<R>.extractDataFromHeaders(description: String = "", block: Headers.() -> Unit): Response<R> {
    headers().block()
    return this
}