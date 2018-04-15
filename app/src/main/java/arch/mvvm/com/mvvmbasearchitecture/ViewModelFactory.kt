package arch.mvvm.com.mvvmbasearchitecture

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import arch.mvvm.com.mvvmbasearchitecture.mainScreen.MainViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(var mViewModel: MainViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return mViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}