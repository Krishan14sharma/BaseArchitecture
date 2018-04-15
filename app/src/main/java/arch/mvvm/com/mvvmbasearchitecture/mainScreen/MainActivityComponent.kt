package arch.mvvm.com.mvvmbasearchitecture.mainScreen

import dagger.Subcomponent

/**
 * Created by krishan on 14/04/18.
 */
@Subcomponent(modules = [(MainActivityModule::class)])
interface MainActivityComponent {
    fun inject(activity: MainActivity)
}