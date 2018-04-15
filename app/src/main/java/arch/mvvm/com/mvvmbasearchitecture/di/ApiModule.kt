package arch.mvvm.com.mvvmbasearchitecture.di

import android.app.Application
import arch.mvvm.com.mvvmbasearchitecture.data.remote.Api
import arch.mvvm.com.mvvmbasearchitecture.data.remote.RemoteRepository
import arch.mvvm.com.mvvmbasearchitecture.data.remote.URL
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by krishan on 14/04/18.
 */
@Module
class ApiModule {

    @Inject
    lateinit var app: Application

    @Provides
    @Singleton
    fun provideApi(): Api {
        return Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(api: Api): RemoteRepository {
        return RemoteRepository(api)
    }

}