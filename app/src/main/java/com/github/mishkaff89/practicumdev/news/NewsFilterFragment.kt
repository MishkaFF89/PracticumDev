package com.github.mishkaff89.practicumdev.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mishkaff89.practicumdev.R
import com.github.mishkaff89.practicumdev.databinding.FragmentNewsFilterBinding
import com.github.mishkaff89.practicumdev.news.adapters.FilterNewsAdapter
import com.github.mishkaff89.practicumdev.news.data.FilterCategory
import com.github.mishkaff89.practicumdev.news.helpers.Filtering
import com.github.mishkaff89.practicumdev.news.helpers.Utils


class NewsFilterFragment : Fragment(), Filtering {

    private lateinit var binding: FragmentNewsFilterBinding

    private var adapter: FilterNewsAdapter? = null

    private var onFilterChanged: ((List<FilterCategory>) -> Unit)? = null

    private val filterCategories by lazy {
        Utils.getCategories(requireContext().applicationContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewsFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() = with(binding){
        with(binding) {
            toolbar.setNavigationOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_apply -> {
                        onFilterChanged?.invoke(filterCategories.categories.filter { filterCategory -> filterCategory.isChecked })
                        requireActivity().supportFragmentManager.popBackStack()
                        true
                    }
                    else -> false
                }
            }
        }
        initAdapter()
    }

    private fun initAdapter(){
        adapter = FilterNewsAdapter(filterCategories.categories) { categoryId, isChecked ->
            filterCategories.categories.find { category -> category.id == categoryId }?.let {
                it.isChecked = isChecked
            }
        }
        binding.rvCategory.adapter = adapter
    }

    override fun onFiltersChanged(onFiltersChanged: (List<FilterCategory>) -> Unit) {
        this.onFilterChanged = onFiltersChanged
    }


}