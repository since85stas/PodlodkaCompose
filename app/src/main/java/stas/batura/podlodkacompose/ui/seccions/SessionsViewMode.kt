package stas.batura.podlodkacompose.ui.seccions

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import stas.batura.podlodkacompose.data.IRepository

private val TAG = "PayViewModel"

class SessionsViewModel @ViewModelInject constructor(val repository: IRepository): ViewModel() {

    private val _toastText = MutableLiveData<String>()
    val toastTex: LiveData<String> get() = _toastText

    private val _spinner = MutableLiveData<Boolean>(false)
    val spinner: LiveData<Boolean> get() = _spinner


    init {
        Log.d(TAG, ": $repository")
        loadData()
    }

    private fun loadData() {
        launchDataLoad {
        }
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _spinner.value = true
                block()
            } catch (error: Throwable) {
                Log.d(TAG, "launchDataLoad: " + error)
                _toastText.value = "Internet problem"
            } finally {
                _spinner.value = false
            }
        }
    }

}