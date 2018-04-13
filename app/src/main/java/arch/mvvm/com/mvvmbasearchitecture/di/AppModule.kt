package arch.mvvm.com.mvvmbasearchitecture.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by krishan on 13/04/18.
 */
@Module
class AppModule(var app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return app
    }

}