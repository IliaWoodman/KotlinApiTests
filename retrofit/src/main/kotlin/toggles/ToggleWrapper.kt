package toggles

internal object ToggleWrapper {

    fun isActive(toggle: Toggles): Boolean {
        return isEnabledByGradle(toggle) ?: return isEnabledByDefault(toggle)
    }

    fun getRealValue(toggle: Toggles): String {
        val key = toggle::class.java.getField(toggle.name).getAnnotation(Toggle::class.java).gradleKey
        return System.getProperty(key) ?: return getDefaultValue(toggle)
    }

    fun setValue(toggle: Toggles, value: String){
        val prop = toggle::class.java.getField(toggle.name).getAnnotation(Toggle::class.java).gradleKey
        System.setProperty(prop, value)
    }

    private fun isEnabledByGradle(toggle: Toggles): Boolean? {
        val key = toggle::class.java.getField(toggle.name).getAnnotation(Toggle::class.java).gradleKey
        val prop = System.getProperty(key)
        if (prop == null) {
            return null
        } else if (prop == "true" || prop == "false") {
            return prop.toBoolean()
        }
        throw RuntimeException("-D$key == \"$prop\". Допустимые значения: true или false")
    }

    private fun isEnabledByDefault(toggle: Toggles): Boolean {
        val annotation = toggle::class.java.getField(toggle.name).getAnnotation(Toggle::class.java)
        return annotation.defaultEnabled
    }

    private fun getDefaultValue(toggle: Toggles): String {
        return toggle::class.java.getField(toggle.name).getAnnotation(Toggle::class.java).defaultValue
    }
}