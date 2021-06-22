package stas.batura.podlodkacompose.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import stas.batura.podlodkacompose.R
import stas.batura.podlodkacompose.data.out.getSessionDays
import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.databinding.DetailFragmentBinding
import stas.batura.podlodkacompose.databinding.SessionsFragmentBinding
import stas.batura.podlodkacompose.ui.sessions.SessionItem
import stas.batura.podlodkacompose.ui.sessions.SessionsViewModel
import stas.batura.podlodkacompose.ui.theme.PodlodkaComposeTheme

private val TAG = DetailFragment::class.java.simpleName

@AndroidEntryPoint
class DetailFragment: Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    @ExperimentalFoundationApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        val bindings: DetailFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.detail_fragment,
            container,
            false
        )

        val args:DetailFragmentArgs by navArgs()

        bindings.apply {
            composeView.setContent {
                PodlodkaComposeTheme() {
                    DetailScreen(session = args.session)
                }
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


}