package arch.mvvm.com.mvvmbasearchitecture.mainScreen

/**
 * Created by krishan on 14/04/18.
 */
interface MainContract {
    interface MainScreen {
        fun render(mainViewState: MainViewState?)
    }

    interface MainPresenter {
        fun getNewsFeedFor(query: String)
    }
}