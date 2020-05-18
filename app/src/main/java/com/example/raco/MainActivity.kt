package com.example.raco

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.raco.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout

        navController = this.findNavController(R.id.nav_host_fragment)
        //set the fragments that should implement the drawer menu
        val topLevelDestinations = setOf(R.id.homeFragment, R.id.addPlayersFragment, R.id.addTrainingsFragment, R.id.viewCalenderFragment,R.id.settingsFragment, R.id.logoutFragment)

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
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(binding.root)) {
            drawerLayout.closeDrawer(binding.root)
        } else {
            super.onBackPressed()
        }
    }
}
