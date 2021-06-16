package stas.batura.podlodkacompose.ui.seccions

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import stas.batura.podlodkacompose.data.IRepository
import stas.batura.podlodkacompose.di.ApplicationScope
import javax.inject.Inject

private val TAG = "PayViewModel"

class SessionsViewModel @ViewModelInject constructor(
    val repository: IRepository,
    @ApplicationScope
    val externalScope: CoroutineScope
    ): ViewModel() {

    private val _toastText = MutableLiveData<String>()
    val toastTex: LiveData<String> get() = _toastText

    private val _spinner = MutableLiveData<Boolean>(false)
    val spinner: LiveData<Boolean> get() = _spinner

//    @Inject
//    @ApplicationScope
//    lateinit var externalScope: CoroutineScope


    init {
        Log.d(TAG, ": $repository")
        loadData()
    }

    private fun loadData() {
        launchDataLoad {
            repository.addInitsessions()
        }
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return externalScope.launch {
            try {
                _spinner.postValue(true)
                block()
            } catch (error: Throwable) {
                Log.d(TAG, "launchDataLoad: " + error)
                _toastText.value = "Internet problem"
            } finally {
                _spinner.postValue(false)
            }
        }
    }

}