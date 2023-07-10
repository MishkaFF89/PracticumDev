package com.github.mishkaff89.practicumdev.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.github.mishkaff89.practicumdev.databinding.FragmentByNkoBinding


class ByNkoFragment : Fragment() {
    private lateinit var binding: FragmentByNkoBinding
    private var adapter: SearchAdapter? = null
    private val searchResults = listOf(
        SearchItem( "Благотворительный фонд Алины Кабаевой"),
        SearchItem("«Во имя жизни»" ),
        SearchItem("Благотворительный Фонд В. Потанина" ),
        SearchItem( "«Детские домики»"),
        SearchItem( "«Мозаика счастья»"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentByNkoBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        initAdapter()
    }

    private fun initAdapter(){
        adapter = SearchAdapter(searchResults.shuffled())
        binding.rcSearch.adapter = adapter
        binding.rcSearch.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }
}