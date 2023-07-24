package com.github.mishkaff89.practicumdev.help

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mishkaff89.practicumdev.R
import com.github.mishkaff89.practicumdev.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {
    private lateinit var binding: FragmentHelpBinding
    private var adapter:HelpAdapter? = null
    private val listCategory by lazy { listOf(
        HelpCategoryItem( resources.getString(R.string.kids), R.raw.kids),
        HelpCategoryItem( resources.getString(R.string.adult), R.raw.adults),
        HelpCategoryItem( resources.getString(R.string.elderly), R.raw.elderly),
        HelpCategoryItem( resources.getString(R.string.animals), R.raw.animals),
        HelpCategoryItem( resources.getString(R.string.events), R.raw.events)
    ) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHelpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter(){
        adapter = HelpAdapter(listCategory)
        binding.rcCategory.adapter = adapter
    }


}