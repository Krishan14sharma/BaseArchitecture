package arch.mvvm.com.mvvmbasearchitecture.data

import io.reactivex.Single

/**
 * Created by krishan on 14/04/18.
 */
interface Repository {
    fun getNews(query: String): Single<News>
}