package arch.mvvm.com.mvvmbasearchitecture

import android.app.Application
import arch.mvvm.com.mvvmbasearchitecture.di.ApiModule
import arch.mvvm.com.mvvmbasearchitecture.di.AppComponent
import arch.mvvm.com.mvvmbasearchitecture.di.AppModule
import arch.mvvm.com.mvvmbasearchitecture.di.DaggerAppComponent

/**
 * Created by krishan on 14/04/18.
 */
class MainApplication : Application() {

    lateinit var mainAppComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        mainAppComponent = DaggerAppComponent.builder()
                .apiModule(ApiModule())
                .appModule(AppModule(this))
                .build().also { it.inject(this) }

    }

}