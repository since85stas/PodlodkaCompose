package stas.batura.podlodkacompose.ui.sessions

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import stas.batura.podlodkacompose.data.IRepository
import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.data.room.SessionFav
import stas.batura.podlodkacompose.data.room.combineSessionsWithFavs
import stas.batura.podlodkacompose.di.ApplicationScope

private val TAG = SessionsViewModel::class.java.simpleName

class SessionsViewModel @ViewModelInject constructor(
    val repository: IRepository,
    @ApplicationScope val externalScope: CoroutineScope
    ): ViewModel() {

    private val _toastText = MutableLiveData<String>()
    val toastTex: LiveData<String> get() = _toastText

    private val _spinner = MutableLiveData<Boolean>(false)
    val spinner: LiveData<Boolean> get() = _spinner

    // список сессий
    val sessions = repository.getSessions().asLiveData()

    // список избранных сессий
    val favSessions = repository.getFavSessions().asLiveData()

    val favourites = repository.getFavourites().asLiveData()

    // список сессий с избранным
    val sessWithFavAv: LiveData<List<SessionFav>> = repository.getSessions().combine(repository.getFavourites()) { s, f ->
        combineSessionsWithFavs(s,f)
    }.asLiveData()

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

    // добавляем сессию в избранное
    fun addToFav(session: Session) {
        launchDataLoad {
            val res = repository.insertFav(session = session)
            if (res != "") {
                _toastText.postValue(res)
            }
        }
    }

    // убираем сессию из избранного
    fun remFromFav(session: Session) {
        repository.deleteFav(session = session)
    }

}