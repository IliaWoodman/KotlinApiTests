package helpers

import Project
import ProjectName.*
import clients.BaseClient
import helpers.ConfiguratorHelper.getGitHubBaseUrl

// TODO Сделать конфигурацию урла
object ConfiguratorHelper {
    @Deprecated("Используй getProject(clazz: Class<T>)",  ReplaceWith("getProject()"))
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

    private fun getGitHubBaseUrl(): String {
        val env: String = System.getProperty("env") ?: return "https://api.github.com"
        return when (env) {
            "dev" -> "https://api.github.com"
            "release" -> "https://release.api.github.com"
            "prod" -> "https://prod.api.github.com"
            else -> env
        }
    }

    // TODO Дописать логику: разные проекты и разные энвы
    fun <T : BaseClient> getProject(clazz: Class<T>): String {
        val project = clazz.getAnnotation(Project::class.java)
            ?: throw NullPointerException("Возможно, забыл навесить аннотацию @Project на $clazz")
        return when (project.projectName) {
            GITHUB -> getGitHubBaseUrl()
            BOOK_STORE -> "BookStoreUrl"
            PET_STORE -> "PetStoreUrl"
        }
    }
}

// TODO сделать конструктор урла аля "${protocol}://${prefix}.${env}.${domain}/${endpoint}";

enum class Environment(val env: String) {
    DEV("asd"),
    RELEASE("gs"),
    PROD("gsd")
}