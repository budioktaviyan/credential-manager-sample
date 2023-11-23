package id.android.credential.sample

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.credentials.CredentialManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import id.android.credential.sample.databinding.FragmentSignInBinding
import kotlinx.coroutines.launch

class SignInFragment : Fragment() {

  private lateinit var credentialManager: CredentialManager
  private var _binding: FragmentSignInBinding? = null

  private val binding get() = _binding!!

  private lateinit var listener: SignInFragmentCallback

  override fun onAttach(context: Context) {
    super.onAttach(context)
    try {
      listener = context as SignInFragmentCallback
    } catch (castException: ClassCastException) {
      /** The activity does not implement the listener */
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentSignInBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    credentialManager = CredentialManager.create(requireActivity())
    binding.signInWithSavedCredentials.setOnClickListener(signInWithSavedCredentials())
  }

  private fun signInWithSavedCredentials(): View.OnClickListener {
    return View.OnClickListener {
      lifecycleScope.launch {
        configureViews(View.VISIBLE, false)
        // TODO : Call getSavedCredentials() method to signin using passkey/password
        configureViews(View.INVISIBLE, true)
        // TODO : complete the authentication process after validating the public key credential to your server and let the user in.
      }
    }
  }

  private fun configureViews(visibility: Int, flag: Boolean) {
    configureProgress(visibility)
    binding.signInWithSavedCredentials.isEnabled = flag
  }

  private fun configureProgress(visibility: Int) {
    binding.textProgress.visibility = visibility
    binding.circularProgressIndicator.visibility = visibility
  }

  private fun fetchAuthJsonFromServer(): String {
    // TODO fetch authentication mock json
    return ""
  }

  private fun sendSignInResponseToServer(): Boolean {
    return true
  }

  private suspend fun getSavedCredentials(): String? {
    // TODO create a GetPublicKeyCredentialOption() with necessary authentication json from server
    // TODO create a PasswordOption to retrieve all the associated user's password
    // TODO call getCredential() with required credential options

    return null
  }

  override fun onDestroyView() {
    super.onDestroyView()
    configureProgress(View.INVISIBLE)
    _binding = null
  }

  interface SignInFragmentCallback {

    fun showHome()
  }
}