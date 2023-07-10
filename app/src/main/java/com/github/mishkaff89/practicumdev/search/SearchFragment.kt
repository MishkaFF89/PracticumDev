package com.github.mishkaff89.practicumdev.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.mishkaff89.practicumdev.R
import com.github.mishkaff89.practicumdev.databinding.FragmentSearchBinding
import com.google.android.material.tabs.TabLayoutMediator

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val fragments = listOf(EventsFragment(),ByNkoFragment())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabLayout()
    }

    private fun initTabLayout() {
        with(binding) {
            val vpAdapter = SearchTabsAdapter(requireActivity(), fragments)
            viewpager.adapter = vpAdapter
            TabLayoutMediator(tablayout, viewpager) { tab, position ->
                when (position) {
                    0 -> tab.text = resources.getString(R.string.events)
                    1 -> tab.text = resources.getString(R.string.by_nko)
                }
            }.attach()
        }
    }

    private class SearchTabsAdapter(
        fragmentActivity: FragmentActivity,
        private val tabFragments: List<Fragment>,
    ) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return tabFragments[position]
        }
    }


}