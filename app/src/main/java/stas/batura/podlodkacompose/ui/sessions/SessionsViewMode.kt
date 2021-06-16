package stas.batura.podlodkacompose.ui.sessions

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import stas.batura.podlodkacompose.data.IRepository
import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.di.ApplicationScope

private val TAG = "PayViewModel"

data class SessionsState(
    var sessions: List<Session>? = null
)

fun invokeState(sessions: List<Session>?): SessionsState {
    return SessionsState(sessions)
}

class SessionsViewModel @ViewModelInject constructor(
    val repository: IRepository,
    @ApplicationScope val externalScope: CoroutineScope
    ): ViewModel() {

    private val _state = MutableLiveData<SessionsState>()
    val state: LiveData<SessionsState> get() = _state

    private val _toastText = MutableLiveData<String>()
    val toastTex: LiveData<String> get() = _toastText

    private val _spinner = MutableLiveData<Boolean>(false)
    val spinner: LiveData<Boolean> get() = _spinner

    val sessions = repository.getSessions().asLiveData()

    val days = repository.getDays().asLiveData()

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
                _toastText.postValue("Internet problem")
            } finally {
                _spinner.postValue(false)
            }
        }
    }

}