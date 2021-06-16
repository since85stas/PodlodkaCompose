package stas.batura.podlodkacompose.ui.sessions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import stas.batura.podlodkacompose.R
import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.databinding.SessionsFragmentBinding
import stas.batura.podlodkacompose.ui.theme.PodlodkaComposeTheme

private val TAG = "SessionsFragment"

@AndroidEntryPoint
class SessionsFragment: Fragment() {

    companion object {
        fun newInstance() = SessionsFragment()
    }

    private lateinit var sessionsViewModel: SessionsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        sessionsViewModel = ViewModelProvider(this).get(SessionsViewModel::class.java)

        val bindings: SessionsFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.sessions_fragment,
            container,
            false
        )
        bindings.apply {
            composeView.setContent {
                Greeting(name = "Test")
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

    /**
     * Stateless component that is responsible for the entire screen.
     *
     * @param sess (state) list of [TodoItem] to display
     */
    @Composable
    fun SessionsScreen(
        sess: List<Session>
    ) {
        Column {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(top = 8.dp)
            ) {
                items(sess){ session ->
                    SessionItem(session = session, modifier = Modifier.fillParentMaxWidth())
                }
            }

            // For quick testing, a random item generator button
            Button(
                onClick = {  },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {
                Text("Add random item")
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        PodlodkaComposeTheme {
            Greeting("Android")
        }
    }

}