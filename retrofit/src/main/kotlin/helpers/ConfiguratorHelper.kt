package helpers

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