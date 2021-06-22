package stas.batura.podlodkacompose.ui.detail

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import stas.batura.podlodkacompose.data.IRepository
import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.di.ApplicationScope

private val TAG = DetailViewModel::class.java.simpleName


class DetailViewModel @ViewModelInject constructor(
    val repository: IRepository,
    @ApplicationScope val externalScope: CoroutineScope
): ViewModel() {

    private val _toastText = MutableLiveData<String>()
    val toastTex: LiveData<String> get() = _toastText

    private val _spinner = MutableLiveData<Boolean>(false)
    val spinner: LiveData<Boolean> get() = _spinner

    val sessions = repository.getSessions().asLiveData()

//    val days = repository.getDays().asLiveData()

    init {
        Log.d(TAG, ": $repository")
    }

    private fun loadData() {
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return externalScope.launch {
            try {
                _spinner.postValue(true)
                block()
            } catch (error: Throwable) {
                Log.d(TAG, "launchDataLoad: " + error)
                _toastText.postValue("Internet problem")
            } finally {
                _spinner.postValue(false)
            }
        }
    }

}