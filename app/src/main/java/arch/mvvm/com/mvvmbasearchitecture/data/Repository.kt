package arch.mvvm.com.mvvmbasearchitecture.data

import io.reactivex.Single

/**
 * Created by krishan on 13/04/18.
 */
interface Repository {
    fun getPosts(): Single<List<Post>>
}