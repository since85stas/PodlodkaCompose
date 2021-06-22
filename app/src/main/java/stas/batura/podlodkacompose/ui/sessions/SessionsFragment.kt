package stas.batura.podlodkacompose.ui.sessions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import stas.batura.podlodkacompose.R
import stas.batura.podlodkacompose.data.out.getSessionDays
import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.data.room.SessionFav
import stas.batura.podlodkacompose.databinding.SessionsFragmentBinding

private val TAG = SessionsFragment::class.java.simpleName

@AndroidEntryPoint
class SessionsFragment: Fragment() {

    companion object {
        fun newInstance() = SessionsFragment()
    }

    @ExperimentalFoundationApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val viewModel = ViewModelProvider(this).get(SessionsViewModel::class.java)

        val bindings: SessionsFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.sessions_fragment,
            container,
            false
        )
        bindings.apply {
            composeView.setContent {
                val favSess: List<Session> by viewModel.favSessions.observeAsState(initial = emptyList())
                val sess: List<SessionFav> by viewModel.sessWithFavAv.observeAsState(initial = emptyList())

                // сортируем сессии по дням
                val gr = getSessionDays(sess)
                SessionsScreen(
                    grouped= gr,
                    favSess = favSess,
                    onSessClick = ::goToDetailFragment,
                    addToFavClick = viewModel::addToFav,
                    remFromFavClick = viewModel::remFromFav
                    )
            }
        }

        viewModel.toastTex.observe(viewLifecycleOwner) {
            Log.d(TAG, "onCreateView: $it")
            Snackbar.make(
                bindings.root,
                it,
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return bindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

    // переходим на фрагмент с информацией
    private fun goToDetailFragment(session: Session) {
        val action = SessionsFragmentDirections.actionSessionsFragmentToDetailFragment(session)
        findNavController().navigate(action)
    }



}