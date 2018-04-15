package arch.mvvm.com.mvvmbasearchitecture.di

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import arch.mvvm.com.mvvmbasearchitecture.ViewModelFactory
import arch.mvvm.com.mvvmbasearchitecture.mainScreen.MainViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by krishan on 14/04/18.
 */
@Module
class AppModule(var app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return app
    }

    @Provides
    fun provideMainViewModel(viewModel: MainViewModel): ViewModel {
        return viewModel
    }

    @Provides
    fun provideMainViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory {
        return factory
    }

}