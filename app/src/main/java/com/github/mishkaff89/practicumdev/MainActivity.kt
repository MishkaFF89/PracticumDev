package com.github.mishkaff89.practicumdev

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mishkaff89.practicumdev.databinding.ActivityMainBinding
import com.github.mishkaff89.practicumdev.help.HelpFragment
import com.github.mishkaff89.practicumdev.news.NewsFragment
import com.github.mishkaff89.practicumdev.search.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationSetup()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, HelpFragment())
            .commit()
    }

    private fun navigationSetup() {
        binding.bottomNavigationView.selectedItemId = R.id.nav_help
        binding.bottomNavigationView.setOnItemSelectedListener {menuId ->
            when(menuId.itemId){
                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction().run {
                        replace(R.id.fragmentContainerView, ProfileFragment())
                        commit()
                    }
                    true
                }
                R.id.nav_help -> {
                    supportFragmentManager.beginTransaction().run {
                        replace(R.id.fragmentContainerView, HelpFragment())
                        commit()
                    }
                    true
                }
                R.id.nav_search -> {
                    supportFragmentManager.beginTransaction().run {
                        replace(R.id.fragmentContainerView, SearchFragment())
                        commit()
                    }
                    true
                }
                R.id.nav_news -> {
                    supportFragmentManager.beginTransaction().run {
                        replace(R.id.fragmentContainerView, NewsFragment())
                        commit()
                    }
                    true
                }

                else -> {true}
            }
        }
    }
}
