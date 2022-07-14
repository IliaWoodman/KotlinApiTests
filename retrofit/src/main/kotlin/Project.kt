@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Project(val projectName: ProjectName)

enum class ProjectName{
    GITHUB,
    BOOK_STORE,
    PET_STORE
}
