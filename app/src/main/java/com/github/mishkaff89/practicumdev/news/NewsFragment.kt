package com.github.mishkaff89.practicumdev.news

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.github.mishkaff89.practicumdev.R
import com.github.mishkaff89.practicumdev.databinding.FragmentNewsBinding
import com.github.mishkaff89.practicumdev.news.adapters.NewsAdapter
import com.github.mishkaff89.practicumdev.news.data.FilterCategory
import com.github.mishkaff89.practicumdev.news.data.NewsCharity
import com.github.mishkaff89.practicumdev.news.helpers.Constants
import com.github.mishkaff89.practicumdev.news.helpers.Filtering
import com.github.mishkaff89.practicumdev.news.helpers.Utils
import java.util.concurrent.Executors

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private var adapter: NewsAdapter? = null

    private var filterFragment: NewsFilterFragment? = null


    private var news:NewsCharity? = null

    private var changedFilters: List<FilterCategory>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(NEWS, news)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.getSerializable(NEWS)?.let {
            news = it as NewsCharity
            initAdapter()
        } ?: kotlin.run {
            if(adapter == null){
                getNews()
            } else {
                binding.rvNews.adapter = this.adapter
            }
        }
    }



    private fun initAdapter(){
        if (adapter == null) {
            adapter = NewsAdapter(news?.news ?: listOf()){newsId ->
                pushNewsDetail(newsId)
            }
            binding.rvNews.adapter = adapter
        } else {
            binding.rvNews.adapter = adapter
        }
    }

    private fun getNews(){
        binding.progressBar.visibility = View.VISIBLE
        Executors.newSingleThreadExecutor().execute{
            Thread.sleep(5_000)
            news = Utils.getNews(requireContext())
            Handler(Looper.getMainLooper()).post{
                initAdapter()
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun init() = with(binding) {
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_filter -> {
                    startFilterFragment()
                    true
                }

                else -> false
            }
        }

    }

    private fun startFilterFragment() {
        filterFragment = NewsFilterFragment().apply {
            (this as Filtering).onFiltersChanged { filters ->
                changedFilters = filters
                updateNews()
            }
        }
        requireActivity().supportFragmentManager.beginTransaction().run {
            addToBackStack(null)
            replace(R.id.fragmentContainerView, filterFragment!!)
            commit()
        }
    }

    private fun updateNews() {
        val filteredNews = news?.news?.filter { news ->
            val filter = changedFilters?.find { filter -> filter.id == news.categoryId }
            filter != null
        }
        adapter?.updateNews(filteredNews ?: listOf())
    }

    private fun pushNewsDetail(newsId: Int) {
        requireActivity().supportFragmentManager.beginTransaction().run {
            addToBackStack(this::class.java.name)
            replace(
                R.id.fragmentContainerView,
                NewsDetailFragment().apply {
                    arguments = bundleOf(Constants.NEWS_ID to newsId)
                })
            commit()
        }
    }

    companion object{
        const val NEWS = "NEWS"
    }

}