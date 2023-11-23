package id.android.credential.sample

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.android.credential.sample.databinding.FragmentMainBinding

class MainFragment : Fragment() {

  private lateinit var listener: MainFragmentCallback
  private var _binding: FragmentMainBinding? = null

  private val binding get() = _binding!!

  override fun onAttach(context: Context) {
    super.onAttach(context)
    try {
      listener = context as MainFragmentCallback
    } catch (castException: ClassCastException) {
      /** The activity does not implement the listener */
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?): View {
    _binding = FragmentMainBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.signUp.setOnClickListener {
      listener.signup()
    }

    binding.signIn.setOnClickListener {
      listener.signIn()
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  interface MainFragmentCallback {

    fun signup()
    fun signIn()
  }
}