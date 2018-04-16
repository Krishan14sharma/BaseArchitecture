package arch.mvvm.com.mvvmbasearchitecture.helper

import android.view.View

/**
 * Created by krishan on 16/04/18.
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}