package arch.mvvm.com.mvvmbasearchitecture.mainScreen

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import arch.mvvm.com.mvvmbasearchitecture.data.Feed
import arch.mvvm.com.mvvmbasearchitecture.data.News
import arch.mvvm.com.mvvmbasearchitecture.data.remote.RemoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by krishan on 14/04/18.
 */
class MainViewModel @Inject constructor(var remote: RemoteRepository) : ViewModel() {

    private var disposable: DisposableSingleObserver<News>? = null
    private var viewState = MutableLiveData<MainViewState>()
    private var state: MainViewState = MainViewState(true, listOf(), false)

    fun getNewsFeedFor(query: String) {
        viewState.value = state.copy(isLoading = true)
        disposable = remote.getNews(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<News>() {
                    override fun onSuccess(news: News) {
                        Log.d("main", news.feeds.size.toString())
                        viewState.value = state.copy(isLoading = false, feeds = news.feeds, isError = false)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        viewState.value = state.copy(isLoading = false, isError = true)
                    }

                })
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()

    }

    fun getViewState(): LiveData<MainViewState> {
        return viewState
    }

}

data class MainViewState(val isLoading: Boolean, val feeds: List<Feed>, val isError: Boolean)