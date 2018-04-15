package arch.mvvm.com.mvvmbasearchitecture.di

import android.app.Application
import arch.mvvm.com.mvvmbasearchitecture.mainScreen.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by krishan on 14/04/18.
 */
@Singleton
@Component(modules = [(ApiModule::class), (AppModule::class)])
interface AppComponent {
    fun inject(app: Application)
    fun inject(activity: MainActivity)
//    fun plus(mainActivityModule: MainActivityModule): MainActivityComponent
}