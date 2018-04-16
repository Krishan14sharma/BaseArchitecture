package arch.mvvm.com.mvvmbasearchitecture.mainScreen

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import arch.mvvm.com.mvvmbasearchitecture.R
import arch.mvvm.com.mvvmbasearchitecture.data.PullRequest

/**
 * Created by krishan on 14/04/18.
 */
class NewsAdapter(private val pullRequests: List<PullRequest>) : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): NewsAdapter.NewsHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news_feed, parent, false) as View
        return NewsHolder(view)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.render(pullRequests[position])
    }

    override fun getItemCount() = pullRequests.size


    class NewsHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun render(pullRequest: PullRequest) {
            val textView = view.findViewById<TextView>(R.id.textView)
            textView.text = "Dummy"
        }
    }

}