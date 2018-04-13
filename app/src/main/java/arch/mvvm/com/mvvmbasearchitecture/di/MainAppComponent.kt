package arch.mvvm.com.mvvmbasearchitecture.di

import android.app.Application
import arch.mvvm.com.mvvmbasearchitecture.postScreen.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by krishan on 13/04/18.
 */
@Singleton
@Component(modules = [(ApiModule::class), (AppModule::class)])
interface MainAppComponent {
    fun inject(app: Application)
    fun inject(activity: MainActivity)
}