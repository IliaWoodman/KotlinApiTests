package helpers

import io.qameta.allure.Allure
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer

// TODO доделать
// Пока примитивный логгер
// В планах добавлять всю эту историю в алюр и писать в лог
class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val method = request.method()
        val url = request.url()
        val headers = request.headers()
        val buffer = Buffer()
        request.body()?.writeTo(buffer)
        val requestBody = buffer.readUtf8()
        val requestLog = "---> Thread: ${Thread.currentThread().id}\nREQUEST method - ${method}\nREQUEST url - ${url}\nREQUEST body - ${requestBody ?: ""}\nREQUEST headers: \n${headers ?: ""}"
        println(requestLog)

        val startTime = System.nanoTime()
        val response = chain.proceed(request)
        val endTime = System.nanoTime()
        val content = response.body()?.string() ?: throw IllegalArgumentException()
        val newRespBody = ResponseBody.create(response.body()?.contentType(), content)

        val responseCode = response.code()
        val responseContentType = response.body()?.contentType()
        val responseHeaders = response.headers()
        val responseLog = "<---- Thread: ${Thread.currentThread().id} Time: ${(endTime - startTime) / 1e6} ms\nRESPONSE code - ${responseCode}\nRESPONSE content-type ${responseContentType}\nRESPONSE body - ${content}\nRESPONSE headers: \n${responseHeaders}"
        println(responseLog)

        Allure.addAttachment("Thread: ${Thread.currentThread().id}",
            "REQUEST:\n" +
                    "Method - $method\n" +
                    "Url - $url\n" +
                    "Headers:\n $headers\n" +
                    "Body: \n $requestBody\n\n" +
                    "RESPONSE:\n" +
                    "Time ${(endTime - startTime) / 1e6}\n"+
                    "Code - $responseCode\n" +
                    "Content-Type - $responseContentType\n" +
                    "Body:\n $content" +
                    "Headers: $responseHeaders")
        return response.newBuilder().body(newRespBody).build()
    }
}