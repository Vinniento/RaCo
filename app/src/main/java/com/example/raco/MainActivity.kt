package com.example.raco

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.raco.databinding.ActivityMainBinding
import com.example.raco.ui.viewmodels.SharedViewModelUser
import kotlinx.android.synthetic.main.nav_drawer_header.view.*

class MainActivity : AppCompatActivity(), DrawerInterface
/* NavigationView.OnNavigationItemSelectedListener */ {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var _sharedViewModel: SharedViewModelUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout

        _sharedViewModel = ViewModelProvider(this).get(SharedViewModelUser::class.java)

        _sharedViewModel.loggedInUser.observe(this, Observer {
            if (it != null) {
                drawerLayout.drawerFirstname.text = "${it.firstname} ${it.lastname}"
                drawerLayout.drawerEmail.text = it.email
                hideKeyboard()
                actionBar?.show()
            } else {
                actionBar?.hide()
                closeDrawer()
            }
        })
        navController = this.findNavController(R.id.nav_host_fragment)
        //set the fragments that should implement the drawer menu
        val topLevelDestinations = setOf(
            R.id.homeFragment,
            R.id.addPlayersFragment,
            R.id.addTrainingsFragment,
            R.id.viewCalenderFragment,
            R.id.settingsFragment,
            R.id.logoutFragment
        )


        //set multiple top-level destinations so that menu drawer is shown in that fragments
        // JVM > 1.8 use:
        //      val appBarConfiguration = AppBarConfiguration(fragmentsWithMenuDrawer)
        appBarConfiguration = AppBarConfiguration.Builder(topLevelDestinations)
            .setDrawerLayout(drawerLayout)
            .build()

        // Set up ActionBar
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Set up navigation menu
        binding.navigationView.setupWithNavController(navController)
        // navigation_view.setNavigationItemSelectedListener(this)

//        if (_sharedViewModel.loggedInUser.value == null) {
//            actionBar?.hide()
//            closeDrawer()
//        } else
//            actionBar?.show()

    }

    /*   override fun onNavigationItemSelected(item: MenuItem): Boolean {
           when (item.itemId) {
               R.id.logoutFragment -> {
                   Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
                   navController.navigate(R.id.loginFragment)
               }
           }
           return true
       } */

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        drawerLayout.closeDrawers()

    }

    override fun openDrawer() {
        drawerLayout.setDrawerLockMode((DrawerLayout.LOCK_MODE_UNLOCKED))
    }

    override fun closeDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

    }
    /*fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }*/

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    override fun changeHeaderFields(email: String?, firstname: String, lastname: String) {
        drawerLayout.drawerEmail.text = email
        drawerLayout.drawerFirstname.text = "$firstname $lastname"
    }
}
