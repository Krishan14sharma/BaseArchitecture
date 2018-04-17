package arch.mvvm.com.mvvmbasearchitecture.mainScreen

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import arch.mvvm.com.mvvmbasearchitecture.R
import arch.mvvm.com.mvvmbasearchitecture.data.PullRequest
import com.squareup.picasso.Picasso

/**
 * Created by krishan on 14/04/18.
 */
class RequestAdapter(private val pullRequests: List<PullRequest>) : RecyclerView.Adapter<RequestAdapter.RequestHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RequestAdapter.RequestHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_requests, parent, false) as View
        return RequestHolder(view)
    }

    override fun onBindViewHolder(holder: RequestHolder, position: Int) {
        holder.render(pullRequests[position])
    }

    override fun getItemCount() = pullRequests.size


    class RequestHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val titleView = view.findViewById<TextView>(R.id.title)
        private val nameView = view.findViewById<TextView>(R.id.name)
        private val avatarView = view.findViewById<ImageView>(R.id.avatar)

        fun render(pullRequest: PullRequest) {
            with(pullRequest) {
                nameView.text = user.login
                titleView.text = body
                Picasso.get().load(user.avatar_url)
                        .error(R.drawable.ic_account_box_black_48dp)
                        .placeholder(R.drawable.ic_account_box_black_48dp).into(avatarView)
            }
        }
    }

}