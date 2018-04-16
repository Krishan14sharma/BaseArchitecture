package arch.mvvm.com.mvvmbasearchitecture.mainScreen

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import arch.mvvm.com.mvvmbasearchitecture.MainApplication
import arch.mvvm.com.mvvmbasearchitecture.R
import arch.mvvm.com.mvvmbasearchitecture.data.PullRequest
import arch.mvvm.com.mvvmbasearchitecture.helper.hide
import arch.mvvm.com.mvvmbasearchitecture.helper.show
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initDi()
        viewManager = LinearLayoutManager(this)
        recyclerView.apply {
            layoutManager = viewManager
        }

        mainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel::class.java)

        mainViewModel.getViewState().observe(this, Observer {
            render(it)
        })
    }

    private fun render(mainViewState: MainViewState?) {
        mainViewState?.apply {
            when (isLoading) {
                true -> progressBar.show()
                false -> progressBar.hide()
            }
            when (isEmpty) {
                true -> emptyView.show()
                false -> emptyView.hide()
            }
            when (isError) {
                true -> errorView.show()
                false -> errorView.hide()
            }
        }
//        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(recyclerView.windowToken, 0)
    }

    private fun display(list: List<PullRequest>) {
        viewAdapter = NewsAdapter(list)
        recyclerView.adapter = viewAdapter
    }

    private fun initDi() {
        (application as MainApplication).mainAppComponent.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
