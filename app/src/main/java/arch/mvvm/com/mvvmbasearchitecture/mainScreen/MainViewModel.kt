package arch.mvvm.com.mvvmbasearchitecture.mainScreen

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import arch.mvvm.com.mvvmbasearchitecture.data.PullRequest
import arch.mvvm.com.mvvmbasearchitecture.data.remote.RemoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by krishan on 14/04/18.
 */
class MainViewModel @Inject constructor(var remote: RemoteRepository) : ViewModel() {

    private var disposable: DisposableSingleObserver<List<PullRequest>>? = null
    private var viewState = MutableLiveData<MainViewState>()
    private var state: MainViewState = MainViewState(true, listOf(), false)

    fun getNewsFeedFor(query: String) {
        viewState.value = state.copy(isLoading = true)
        disposable = remote.openPullRequests("jakeWharton", "butterknife")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<PullRequest>>() {
                    override fun onSuccess(pullRequests: List<PullRequest>) {
                        Log.d("main", pullRequests.size.toString())
//                        viewState.value = state.copy(isLoading = false, feeds = pullRequests.feeds, isError = false)
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

data class MainViewState(val isLoading: Boolean, val feeds: List<PullRequest>, val isError: Boolean)