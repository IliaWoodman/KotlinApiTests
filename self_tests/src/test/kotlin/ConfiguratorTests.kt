import ProjectName.*
import clients.BaseClient
import helpers.ConfiguratorHelper.getProject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class ConfiguratorTests {
    @Test
    fun gitHubDefaultBaseUrl() {
        val baseUrl = getProject(TestGitHubClient::class.java)
        assertEquals("https://api.github.com", baseUrl)
    }

    @Test
    fun gitHubReleaseBaseUrl() {
        System.setProperty("env", "release")
        val baseUrl = getProject(TestGitHubClient::class.java)
        assertEquals("https://release.api.github.com", baseUrl)
        System.clearProperty("env")
    }

    @Test
    fun gitHubProdBaseUrl() {
        System.setProperty("env", "prod")
        val baseUrl = getProject(TestGitHubClient::class.java)
        assertEquals("https://prod.api.github.com", baseUrl)
        System.clearProperty("env")
    }

    @Test
    fun gitHubDevBaseUrl() {
        System.setProperty("env", "dev")
        val baseUrl = getProject(TestGitHubClient::class.java)
        assertEquals("https://api.github.com", baseUrl)
        System.clearProperty("env")
    }

    @Test
    fun invalidClientBaseUrl() {
        val exception = assertThrows(NullPointerException::class.java) {
            getProject(InvalidTestClient::class.java)
        }
        assertEquals("Возможно, забыл навесить аннотацию @Project на class InvalidTestClient", exception.message)
    }

    @Test
    fun getPetStoreBaseUrl() {
        val baseUrl = getProject(TestPetStoreClient::class.java)
        assertEquals("PetStoreUrl", baseUrl)
    }

    @Test
    fun getBookStoreBaseUrl() {
        val baseUrl = getProject(TestBookStoreClient::class.java)
        assertEquals("http://localhost:8081/api/", baseUrl)
    }
}

private class InvalidTestClient : BaseClient

@Project(GITHUB)
private class TestGitHubClient : BaseClient

@Project(BOOK_STORE)
private class TestBookStoreClient : BaseClient

@Project(PET_STORE)
private class TestPetStoreClient : BaseClient