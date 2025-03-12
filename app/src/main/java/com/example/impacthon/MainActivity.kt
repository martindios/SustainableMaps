package com.example.impacthon

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.impacthon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
    }

    fun updateBottomNavigationView() {
        val navView: BottomNavigationView = binding.navView
        navView.menu.findItem(R.id.navigation_profile).title = getString(R.string.title_profile)
        navView.menu.findItem(R.id.navigation_map).title = getString(R.string.title_map)
        navView.menu.findItem(R.id.navigation_settings).title = getString(R.string.title_settings)
    }
}
