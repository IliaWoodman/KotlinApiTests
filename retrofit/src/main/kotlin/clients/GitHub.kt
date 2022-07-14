package clients

import helpers.ConfiguratorHelper
import models.Contributor
import models.Issue
import retrofit2.Call
import retrofit2.http.*

// TODO написать свою аннотацию на класс в которую указываем проект и по этой аннотации будем подставлять бейсУрл
interface GitHub {
    @GET("/repos/{owner}/{repo}/contributors")
    fun contributors(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): Call<MutableList<Contributor>>

    @POST("/repos/{owner}/{repo}/issues")
    fun createIssue(
        @Url url: String = ConfiguratorHelper.getBaseUrl(),
        @Body issue: Issue,
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<Contributor>
}

// TODO убрать и придумать как лучше делать с хедерами
//val defaultHeader = mutableMapOf("Content" to "json")