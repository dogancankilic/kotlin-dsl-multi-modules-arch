package com.dogancan.kotlin_dsl_multi_modules_arch

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.dogancan.kotlin_dsl_multi_modules_arch.extensions.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            initBottomNavigationView()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        initBottomNavigationView()
    }

    private fun initBottomNavigationView() {
        val bottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottom_nav)

        val navGraphs = listOf(
            R.navigation.character,
            R.navigation.episode
        )

        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphs,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        controller.observe(this) { navController ->
            setupActionBarWithNavController(navController)
        }

        currentNavController = controller

        currentNavController?.observe(this) { navController ->
            navController.addOnDestinationChangedListener { _, destination, args ->
                bottomNavigationView.isVisible = destination.id != R.id.characterDetailFragment
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}
