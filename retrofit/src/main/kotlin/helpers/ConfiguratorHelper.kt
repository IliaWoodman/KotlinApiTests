package helpers

import Project
import ProjectName.*
import clients.BaseClient

// TODO Сделать конфигурацию урла
object ConfiguratorHelper {
    fun getBaseUrl(): String {
        val env: String? = System.getProperty("env")
        val url = when (env) {
            "dev" -> Environment.DEV.env
            "release" -> Environment.RELEASE.env
            "prod" -> Environment.PROD.env
            else -> "https://api.github.com"
        }
        return url
    }
}

// TODO сделать конструктор урла аля "${protocol}://${prefix}.${env}.${domain}/${endpoint}";

enum class Environment(val env: String) {
    DEV("asd"),
    RELEASE("gs"),
    PROD("gsd")
}
// TODO Дописать логику: разные проекты и разные энвы
fun <T: BaseClient> getProject(clazz: Class<T>): String {
    val project = clazz.getAnnotation(Project::class.java) ?: throw IllegalArgumentException("Неверный проект")
    return when (project.projectName) {
        GITHUB -> "https://api.github.com"
        BOOK_STORE -> ""
        PET_STORE -> ""
    }
}