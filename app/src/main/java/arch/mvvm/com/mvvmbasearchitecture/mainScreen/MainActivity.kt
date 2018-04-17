package arch.mvvm.com.mvvmbasearchitecture.mainScreen

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import arch.mvvm.com.mvvmbasearchitecture.MainApplication
import arch.mvvm.com.mvvmbasearchitecture.R
import arch.mvvm.com.mvvmbasearchitecture.helper.hide
import arch.mvvm.com.mvvmbasearchitecture.helper.show
import arch.mvvm.com.mvvmbasearchitecture.helper.toast
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initDi()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        mainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel::class.java)

        mainViewModel.getViewState().observe(this, Observer {
            render(it)
        })
        search.setOnClickListener {
            mainViewModel.getSearchInput()
        }

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
            when (displayList) {
                true -> {
                    viewAdapter = RequestAdapter(pullRequests)
                    recyclerView.adapter = viewAdapter
                    recyclerView.show()
                }
                false -> {
                    viewAdapter = RequestAdapter(pullRequests)
                    recyclerView.adapter = viewAdapter
                    recyclerView.hide()
                }
            }
            when (showToastMessage) {
                true -> {
                    toast(getString(R.string.error_empty_dialog))
                }
            }
            when (showInputDialog) {
                true -> {
                    val inflate = layoutInflater.inflate(R.layout.dialog_input, null)
                    AlertDialog.Builder(this@MainActivity, 0)
                            .setView(inflate)
                            .setPositiveButton("Submit", DialogInterface.OnClickListener { dialog, which ->
                                val ownerName = inflate.findViewById<EditText>(R.id.ownerName).text.toString().trim()
                                val repoName = inflate.findViewById<EditText>(R.id.repoName).text.toString().trim()
                                mainViewModel.getPullRequestsFor(ownerName, repoName)
                                dialog.dismiss()
                            }
                            ).create().show()
                }
            }
        }

    }

    private fun initDi() {
        (application as MainApplication).mainAppComponent.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
