package helpers

import clients.BaseClient
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// хелперы с помощью которых скрываем всю логику и реализовываем внутренюю дополнительную логику
// Для использования в тестах можно использовать только makeRequest()

//Создаем реквест билдер
inline fun <reified T: BaseClient> build(type: Class<T>): T {
    return Retrofit.Builder()
        .baseUrl(ConfiguratorHelper.getProject(type))
        .client(getClient()) // TODO Написать интерсептор и воткнуть сюда // val hhhtp = OkHttpClient.Builder().addInterceptor(Inter()).build()
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(type)
}

// выполнение запроса (нужно будет в дальнейшем для свагер кавераджа и другой логики) или сделать еще один интерсептор для этих вещей
fun <T> Call<T>.customExecute(): Response<T> {
    if (System.getProperty("coverage").toBoolean()) {
        this.request()
    }
    return this.execute()
}

// делаем запрос и возвращаем респонс
inline fun <reified T: BaseClient, R> makeRequest(obj: Class<T>, block: T.() -> Call<R>): Response<R> {
    return build(obj).block().customExecute()
}

fun getClient(): OkHttpClient = // TODO Добавить класс с аннотациями типа Toggle
    OkHttpClient.Builder().addInterceptor(LoggingInterceptor()).build()