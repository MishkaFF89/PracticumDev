package com.github.mishkaff89.practicumdev

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mishkaff89.practicumdev.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationSetup()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, ProfileFragment())
            .commit()
    }

    private fun navigationSetup() {
        binding.bottomNavigationView.selectedItemId = R.id.nav_profile
    }
}
