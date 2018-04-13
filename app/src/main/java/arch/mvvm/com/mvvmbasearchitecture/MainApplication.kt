package arch.mvvm.com.mvvmbasearchitecture

import android.app.Application
import arch.mvvm.com.mvvmbasearchitecture.di.ApiModule
import arch.mvvm.com.mvvmbasearchitecture.di.AppModule
import arch.mvvm.com.mvvmbasearchitecture.di.DaggerMainAppComponent
import arch.mvvm.com.mvvmbasearchitecture.di.MainAppComponent

/**
 * Created by krishan on 13/04/18.
 */
class MainApplication : Application() {

    lateinit var mainAppComponent: MainAppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        mainAppComponent = DaggerMainAppComponent.builder()
                .apiModule(ApiModule())
                .appModule(AppModule(this))
                .build().also { it.inject(this) }

    }

}