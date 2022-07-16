package toggles

import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD

@Retention(RUNTIME)
@Target(FIELD)
annotation class Toggle(
    val name: String,
    val defaultEnabled: Boolean = false,
    val defaultValue: String = "",
    val gradleKey: String
)
