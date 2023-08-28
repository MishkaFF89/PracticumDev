package com.github.mishkaff89.practicumdev

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.mishkaff89.practicumdev.auth.AuthFragment
import com.github.mishkaff89.practicumdev.databinding.ActivityMainBinding
import com.github.mishkaff89.practicumdev.help.HelpFragment
import com.github.mishkaff89.practicumdev.news.NewsFragment
import com.github.mishkaff89.practicumdev.search.SearchFragment

class MainActivity : AppCompatActivity(), Auth, Readable {
    private lateinit var binding: ActivityMainBinding

    private var _subjects: Subjects? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState != null){
            return
        }
        _subjects = Subjects(this)
        supportFragmentManager.beginTransaction().run {
            binding.bottomNavigationView.visibility = View.GONE
            add(R.id.fragmentContainerView, AuthFragment())
            commit()
        }

        navigationSetup()
    }
    override fun onResume() {
        super.onResume()
        setNotificationBadge(_subjects?.subjects?.size ?: 0)
    }

    override val subjects: List<Subjects.Subject>
        get() = _subjects?.subjects!!

    override fun setNotificationBadge(count: Int) {
        if (count == 0) {
            binding.bottomNavigationView.getOrCreateBadge(R.id.nav_news).apply {
                isVisible = false
                number = 0
            }
        } else {
            binding.bottomNavigationView.getOrCreateBadge(R.id.nav_news).apply {
                isVisible = true
                number = count
            }
        }
    }

    override fun setAsRead(id: Int) {
        _subjects?.setAsRead(id)
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

    override fun onAuthSuccess() {
        binding.bottomNavigationView.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction().run {
            replace(R.id.fragmentContainerView, HelpFragment())
            commit()
        }
    }


}
