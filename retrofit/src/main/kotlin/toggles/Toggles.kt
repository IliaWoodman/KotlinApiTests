package toggles

enum class Toggles {
    @Toggle(name = "Многопоточное логирование", defaultEnabled = true, gradleKey = "LOGS")
    LOGS,

    @Toggle(name = "Тогл для юнит тестов", defaultValue = "TestToggle", gradleKey = "TEST_TOGGLE")
    TEST_TOGGLE;

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