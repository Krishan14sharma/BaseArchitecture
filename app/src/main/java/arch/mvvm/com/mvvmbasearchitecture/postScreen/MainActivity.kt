package arch.mvvm.com.mvvmbasearchitecture.postScreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import arch.mvvm.com.mvvmbasearchitecture.MainApplication
import arch.mvvm.com.mvvmbasearchitecture.R
import arch.mvvm.com.mvvmbasearchitecture.data.Post
import arch.mvvm.com.mvvmbasearchitecture.data.remote.RemoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var remote: RemoteRepository
    private var disposable: DisposableSingleObserver<List<Post>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MainApplication).mainAppComponent.inject(this)
        disposable = remote.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Post>>() {
                    override fun onSuccess(t: List<Post>) {
                        Log.d("MainActivity", t.size.toString())
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

}
