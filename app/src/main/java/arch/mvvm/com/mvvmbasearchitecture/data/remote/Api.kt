package arch.mvvm.com.mvvmbasearchitecture.data.remote

import arch.mvvm.com.mvvmbasearchitecture.data.PullRequest
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by krishan on 14/04/18.
 */
const val URL = "https://api.github.com/"

interface Api {
    @GET("repos/{owner}/{repo}/pulls?state=open")
    fun openPullRequests(@Path("owner") owner: String, @Path("repo") repo: String): Single<List<PullRequest>>
}