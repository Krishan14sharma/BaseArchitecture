package arch.mvvm.com.mvvmbasearchitecture.data.remote

import arch.mvvm.com.mvvmbasearchitecture.data.News
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by krishan on 14/04/18.
 */
const val URL = "https://hn.algolia.com/api/v1/"

interface Api {
    @GET("search")
    fun news(@Query("query") query: String): Single<News>
}