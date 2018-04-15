package arch.mvvm.com.mvvmbasearchitecture.data.remote

import arch.mvvm.com.mvvmbasearchitecture.data.News
import arch.mvvm.com.mvvmbasearchitecture.data.Repository
import io.reactivex.Single

/**
 * Created by krishan on 14/04/18.
 */
class RemoteRepository(private val api: Api) : Repository {
    override fun getNews(query: String): Single<News> {
        return api.news(query)
    }
}