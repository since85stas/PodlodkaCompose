package stas.batura.podlodkacompose.ui.seccions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import stas.batura.podlodkacompose.R

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

        return  inflater.inflate(
            R.layout.sessions_fragment,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

}