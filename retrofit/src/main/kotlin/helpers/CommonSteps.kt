package helpers

import io.qameta.allure.Step
import okhttp3.Headers
import org.junit.jupiter.api.Assertions.assertEquals
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

@Step("User check that code is equals {expectedCode}")
fun <R> Response<R>.andCheckCode(expectedCode: Int): Response<R>{
    assertEquals(expectedCode, this.code())
    return this
}
//
//@Step("User validate jsonSchema")
//// TODO Доработать
//fun <R> Response<R>.validateJsonSchema(jsonSchemaPath: String = ""): Response<R> {
//    val schemaPath = Paths.get(
//        Paths.get("").toAbsolutePath().toString() + "/retrofit/src/test/resources/jsonSchemas/${jsonSchemaPath}.json"
//    )
//    val json = JSONObject(Gson().toJson(this.body()))
//
//    val schema = SchemaLoader.load(JSONObject(File(schemaPath.toString()).readText()))
//    schema.validate(json)
//    return this
//}