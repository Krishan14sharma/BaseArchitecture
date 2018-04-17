package arch.mvvm.com.mvvmbasearchitecture.mainScreen

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
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

    init {
        viewState.value = MainViewState(pullRequests = emptyList())
    }


    private fun state(): MainViewState {
        return viewState.value!!
    }

    fun getPullRequestsFor(owner: String?, repo: String?) {
        if (owner.isNullOrEmpty() || repo.isNullOrEmpty()) {
            viewState.value = state().copy(showInputDialog = false, showToastMessage = true)
            return
        }

        viewState.value = getLoadingState()
        disposable = remote.openPullRequests(owner!!, repo!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<PullRequest>>() {
                    override fun onSuccess(pullRequests: List<PullRequest>) {
                        viewState.value = getSuccessState(pullRequests)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        viewState.value = getErrorState()
                    }

                })
    }

    private fun getLoadingState() = MainViewState(isLoading = true, pullRequests = emptyList(), isEmpty = false)

    private fun getSuccessState(pullRequests: List<PullRequest>) = MainViewState(isEmpty = false, displayList = true, pullRequests = pullRequests)

    private fun getErrorState() = MainViewState(isError = true, pullRequests = emptyList(), isEmpty = false)

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()

    }

    fun getViewState(): LiveData<MainViewState> {
        return viewState
    }

    fun getSearchInput() {
        viewState.value = state().copy(showInputDialog = true, showToastMessage = false)
    }

}

data class MainViewState(val isLoading: Boolean = false,
                         val pullRequests: List<PullRequest>,
                         val isError: Boolean = false,
                         val isEmpty: Boolean = true,
                         val displayList: Boolean = false,
                         val showInputDialog: Boolean = false,
                         val showToastMessage: Boolean = false)