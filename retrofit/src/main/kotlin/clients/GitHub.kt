package clients

import Project
import ProjectName.GITHUB
import models.github.Contributor
import models.github.Issue
import retrofit2.Call
import retrofit2.http.*

@Project(GITHUB)
interface GitHub : BaseClient {
    @GET("/repos/{owner}/{repo}/contributors")
    fun contributors(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @HeaderMap headers: Map<String, String> = emptyMap(),
        @QueryMap query: Map<String, String> = emptyMap()
    ): Call<MutableList<Contributor>>

    @POST("/repos/{owner}/{repo}/issues")
    fun createIssue(
        @Body issue: Issue,
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<Contributor>
}

// TODO убрать и придумать как лучше делать с хедерами
//val defaultHeader = mutableMapOf("Content" to "json")