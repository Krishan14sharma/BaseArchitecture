package arch.mvvm.com.mvvmbasearchitecture.data


/**
 * Created by krishan on 14/04/18.
 */


data class PullRequest(
        val id: Int,
        val url: String,
        val number: Int,
        val state: String,
        val title: String,
        val body: String,
        val assignee: Assignee,
        val labels: List<Label>,
        val locked: Boolean,
        val created_at: String,
        val updated_at: String,
        val user: User
)

data class User(
        val login: String,
        val id: Int,
        val avatar_url: String,
        val gravatar_id: String,
        val url: String,
        val type: String,
        val site_admin: Boolean
)

data class Label(
        val id: Int,
        val url: String,
        val name: String,
        val description: String,
        val color: String,
        val default: Boolean
)

data class Assignee(
        val login: String,
        val id: Int,
        val avatar_url: String
)