package arch.mvvm.com.mvvmbasearchitecture.data.remote

import arch.mvvm.com.mvvmbasearchitecture.data.Post
import arch.mvvm.com.mvvmbasearchitecture.data.Repository
import io.reactivex.Single

/**
 * Created by krishan on 13/04/18.
 */
class RemoteRepository(private val api: Api) : Repository {
    override fun getPosts(): Single<List<Post>> {
        return api.post()
    }
}