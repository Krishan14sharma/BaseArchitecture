package arch.mvvm.com.mvvmbasearchitecture.data

import com.google.gson.annotations.SerializedName


/**
 * Created by krishan on 14/04/18.
 */

data class News(
        @SerializedName("hits") val feeds: List<Feed>
)

data class Feed(
        @SerializedName("created_at") val createdAt: String,
        @SerializedName("title") val title: String,
        @SerializedName("url") val url: String,
        @SerializedName("author") val author: String,
        @SerializedName("points") val points: Int,
        @SerializedName("story_text") val storyText: String,
        @SerializedName("comment_text") val commentText: Any,
        @SerializedName("num_comments") val numComments: Int,
        @SerializedName("story_id") val storyId: Any,
        @SerializedName("story_title") val storyTitle: Any,
        @SerializedName("story_url") val storyUrl: Any,
        @SerializedName("parent_id") val parentId: Any,
        @SerializedName("created_at_i") val createdAtI: Int
)
