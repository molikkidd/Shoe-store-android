package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    connect drawer to mainactivity
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

//    create main page activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
//    add side menu when page is being rendered
        drawerLayout = binding.drawerLayout
//connect navcontroller to fragments
    val navController = this.findNavController(R.id.myNavHostFragment)

    navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, _: Bundle? ->
        when (nd.id) {
            nc.graph.startDestination -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
            R.id.shoeListFragment2 -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }
    }
//    connect Navi UI to Navi Controller
    NavigationUI.setupWithNavController(binding.navView, navController)

    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    appBarConfiguration = AppBarConfiguration(navController.graph)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}
