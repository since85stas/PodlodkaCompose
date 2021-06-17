package stas.batura.podlodkacompose.ui.detail

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
import androidx.compose.ui.unit.dp
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import stas.batura.podlodkacompose.R
import stas.batura.podlodkacompose.data.out.getSessionDays
import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.databinding.DetailFragmentBinding
import stas.batura.podlodkacompose.databinding.SessionsFragmentBinding
import stas.batura.podlodkacompose.ui.sessions.SessionItem
import stas.batura.podlodkacompose.ui.sessions.SessionsViewModel

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
        bindings.apply {
            composeView.setContent {
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
     * @param days (state) list of [TodoItem] to display
     */
    @ExperimentalFoundationApi
    @Composable
    fun DetailScreen(
        grouped:  Map<String, List<Session>>
    ) {
        Column {
        }
    }

}