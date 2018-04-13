package arch.mvvm.com.mvvmbasearchitecture.data.remote

import arch.mvvm.com.mvvmbasearchitecture.data.Post
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by krishan on 13/04/18.
 */
const val URL = "https://jsonplaceholder.typicode.com/"

interface Api {
    @GET("posts")
    fun post(): Single<List<Post>>
}