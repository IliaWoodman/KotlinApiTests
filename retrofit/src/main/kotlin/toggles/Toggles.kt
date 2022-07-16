package toggles

enum class Toggles {
    @Toggle(name = "Многопоточное логирование", defaultEnabled = true, gradleKey = "logs")
    LOGS;

    fun isActive() {
    }
}