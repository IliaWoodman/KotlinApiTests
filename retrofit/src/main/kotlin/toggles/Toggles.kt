package toggles

enum class Toggles {
    @Toggle(name = "Многопоточное логирование", defaultEnabled = false, gradleKey = "LOGS")
    LOGS;

    fun isActive(): Boolean {
       return ToggleWrapper.isActive(this)
    }

    fun getValue(): String {
       return ToggleWrapper.getRealValue(this)
    }

    fun setValue(value: String){
        ToggleWrapper.setValue(this, value)
    }

    fun setValue(value: Boolean){
        ToggleWrapper.setValue(this, value.toString())
    }
}