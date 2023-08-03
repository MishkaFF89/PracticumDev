package com.github.mishkaff89.practicumdev.news

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mishkaff89.practicumdev.R
import com.github.mishkaff89.practicumdev.databinding.FragmentNewsFilterBinding
import com.github.mishkaff89.practicumdev.news.adapters.FilterNewsAdapter
import com.github.mishkaff89.practicumdev.news.data.FilterCategory
import com.github.mishkaff89.practicumdev.news.data.FilterNewsCategories
import com.github.mishkaff89.practicumdev.news.helpers.Constants
import com.github.mishkaff89.practicumdev.news.helpers.Filtering
import com.github.mishkaff89.practicumdev.news.helpers.Utils
import java.util.concurrent.Executors


class NewsFilterFragment : Fragment(), Filtering {

    private lateinit var binding: FragmentNewsFilterBinding

    private var adapter: FilterNewsAdapter? = null

    private var onFilterChanged: ((List<FilterCategory>) -> Unit)? = null

    private var filterCategories: FilterNewsCategories? = null

    private var broadcastReceiver: CategoriesBroadcastReceiver? = null


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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(FILTER_CATEGORIES, filterCategories)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.getSerializable(FILTER_CATEGORIES)?.let {
            filterCategories = it as FilterNewsCategories
            initAdapter()
        } ?: kotlin.run {
            getFilterCategoriesWithExecutor()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        broadcastReceiver?.let {
            requireContext().unregisterReceiver(it)
        }
    }

    private fun init(){
        with(binding) {
            toolbar.setNavigationOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_apply -> {
                        filterCategories?.categories?.let { categories ->
                            onFilterChanged?.invoke(categories.filter { category -> category.isChecked })
                        }
                        requireActivity().supportFragmentManager.popBackStack()
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun initAdapter() {
        adapter = FilterNewsAdapter(
            filterCategories?.categories ?: listOf()
        ) { categoryId, isChecked ->
            filterCategories?.categories?.find { category ->
                category.id == categoryId
            }?.let {
                it.isChecked = isChecked
            }
        }
        binding.rvCategory.adapter = adapter
    }



    private fun getFilterCategoriesWithExecutor(){
        binding.progressBar.visibility = View.VISIBLE
        Executors.newSingleThreadExecutor().execute{
            Thread.sleep(5_000)
            filterCategories = Utils.getCategories(requireContext())
            Handler(Looper.getMainLooper()).post{
                initAdapter()
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun getFilterCategoriesWithIntentService(){
        broadcastReceiver = CategoriesBroadcastReceiver()
        requireActivity().startService(
            Intent(
                requireContext(),
                CategoriesIntentService::class.java
            )
        )

        requireContext().registerReceiver(
            broadcastReceiver,
            IntentFilter(Constants.ACTION_SEND_CATEGORIES).apply {
                addCategory(Intent.CATEGORY_DEFAULT)
            }
        )
    }

    override fun onFiltersChanged(onFiltersChanged: (List<FilterCategory>) -> Unit) {
        this.onFilterChanged = onFiltersChanged
    }

    companion object {
        const val FILTER_CATEGORIES = "FILTER_CATEGORIES"
    }

    inner class CategoriesBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val result = intent?.extras?.getSerializable(Constants.EXTRA_CATEGORIES)
            result.let {
                filterCategories = it as FilterNewsCategories
                initAdapter()
            }
        }

    }
}

