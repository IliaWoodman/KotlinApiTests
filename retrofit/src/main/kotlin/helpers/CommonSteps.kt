package helpers

import com.google.gson.Gson
import io.qameta.allure.Step
import okhttp3.Headers
import org.everit.json.schema.loader.SchemaLoader
import org.json.JSONObject
import retrofit2.Response
import java.io.File
import java.nio.file.Paths


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