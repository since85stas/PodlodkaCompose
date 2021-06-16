package stas.batura.podlodkacompose.ui.sessions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import stas.batura.podlodkacompose.R
import stas.batura.podlodkacompose.databinding.SessionsFragmentBinding
import stas.batura.podlodkacompose.ui.theme.PodlodkaComposeTheme

private val TAG = "SessionsFragment"

@AndroidEntryPoint
class SessionsFragment: Fragment() {

    companion object {
        fun newInstance() = SessionsFragment()
    }

    private lateinit var sessionsViewModel: SessionsViewModel

//    private val adapter: UsersAdapter = UsersAdapter()

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