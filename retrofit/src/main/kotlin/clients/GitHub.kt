package clients

import Project
import ProjectName.GITHUB
import helpers.ConfiguratorHelper
import models.Contributor
import models.Issue
import retrofit2.Call
import retrofit2.http.*

@Project(GITHUB)
interface GitHub : BaseClient{
    @GET("/repos/{owner}/{repo}/contributors")
    fun contributors(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
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