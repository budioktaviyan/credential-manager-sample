package id.android.credential.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import id.android.credential.sample.HomeFragment.HomeFragmentCallback
import id.android.credential.sample.MainFragment.MainFragmentCallback
import id.android.credential.sample.R
import id.android.credential.sample.SignInFragment.SignInFragmentCallback
import id.android.credential.sample.SignUpFragment.SignUpFragmentCallback
import id.android.credential.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
  MainFragmentCallback,
  HomeFragmentCallback,
  SignInFragmentCallback,
  SignUpFragmentCallback {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    DataProvider.initSharedPref(applicationContext)
    if (DataProvider.isSignedIn()) {
      showHome()
    } else {
      loadMainFragment()
    }
  }

  override fun signup() {
    loadFragment(SignUpFragment(), false)
  }

  override fun signIn() {
    loadFragment(SignInFragment(), false)
  }

  override fun logout() {
    supportFragmentManager.popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE)
    loadMainFragment()
  }

  private fun loadMainFragment() {
    supportFragmentManager.popBackStack()
    loadFragment(MainFragment(), false)
  }

  override fun showHome() {
    supportFragmentManager.popBackStack()
    loadFragment(HomeFragment(), true, "home")
  }

  private fun loadFragment(fragment: Fragment, flag: Boolean, backstackString: String? = null) {
    DataProvider.configureSignedInPref(flag)
    supportFragmentManager.beginTransaction()
      .replace(R.id.fragment_container, fragment)
      .addToBackStack(backstackString).commit()
  }

  override fun onBackPressed() {
    if (DataProvider.isSignedIn() || supportFragmentManager.backStackEntryCount == 1) {
      finish()
    } else {
      super.onBackPressed()
    }
  }
}