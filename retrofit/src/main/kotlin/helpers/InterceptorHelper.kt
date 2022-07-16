package helpers

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

// TODO доделать
// Пока примитивный логгер
// В планах добавлять всю эту историю в алюр и писать в лог
class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        println("REQUEST method ---> ${request.method()}")
        println("REQUEST url ---> ${request.url()}")
        println("REQUEST headers ---> ${request.headers()}")
        println("REQUEST body ---> ${request.body() ?: "{}"}")
        val response = chain.proceed(request)

        val content = response.body()?.string() ?: throw IllegalArgumentException()
        val newRespBody = ResponseBody.create(response.body()?.contentType(), content)

        println("RESPONSE code ---> ${response.code()}")
        println("RESPONSE content type ---> ${response.body()?.contentType()}")
        println("RESPONSE body ---> $content")
        println("RESPONSE headers ---> ${response.headers()}")
        return response.newBuilder().body(newRespBody).build()
    }
}