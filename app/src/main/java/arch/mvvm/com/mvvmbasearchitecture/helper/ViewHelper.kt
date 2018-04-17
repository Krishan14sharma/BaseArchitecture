package arch.mvvm.com.mvvmbasearchitecture.helper

import android.content.Context
import android.view.View
import android.widget.Toast

/**
 * Created by krishan on 16/04/18.
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(applicationContext, msg, duration).show()
}