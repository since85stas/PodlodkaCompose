package stas.batura.podlodkacompose.ui.sessions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import stas.batura.podlodkacompose.R
import stas.batura.podlodkacompose.data.out.SessionDay
import stas.batura.podlodkacompose.data.out.getSessionDays
import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.databinding.SessionsFragmentBinding
import stas.batura.podlodkacompose.ui.theme.PodlodkaComposeTheme

private val TAG = SessionsFragment::class.java.simpleName

@AndroidEntryPoint
class SessionsFragment: Fragment() {

    companion object {
        fun newInstance() = SessionsFragment()
    }

//    private lateinit var sessionsViewModel: SessionsViewModel

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
//                val days: List<SessionDay> by viewModel.days.observeAsState(initial = emptyList())
                val sess: List<Session> by viewModel.sessions.observeAsState(initial = emptyList())
//                val grouped:  Map<String, List<Session>> = sess.groupBy { it.date }
                val gr = getSessionDays(sess)
                SessionsScreen(
                    grouped= gr,
                    onSessClick = this@SessionsFragment::goToDetailFragment
                    )
            }
        }

        return bindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

    private fun goToDetailFragment(session: Session) {
        val action = SessionsFragmentDirections.actionSessionsFragmentToDetailFragment(session)
        findNavController().navigate(action)
    }



}