package com.github.mishkaff89.practicumdev.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.github.mishkaff89.practicumdev.databinding.FragmentEventsBinding
import com.github.mishkaff89.practicumdev.news.data.NewsCharity
import com.github.mishkaff89.practicumdev.news.data.NewsItem
import com.github.mishkaff89.practicumdev.news.helpers.Utils
import com.github.mishkaff89.practicumdev.search.adpters.EventsAdapter
import com.jakewharton.rxbinding4.InitialValueObservable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class EventsFragment : Fragment(), Searchable {
    private lateinit var binding: FragmentEventsBinding

    private var adapter: EventsAdapter? = null

    private var events: List<NewsItem> = listOf()

    override var searchViewListener: InitialValueObservable<CharSequence>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEventsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.getSerializable(EVENTS)?.let {
            events = (it as NewsCharity).news
            initAdapter()
        }
        binding.placeHolder.visibility = View.GONE
        initAdapter()
        subscribeSearchView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(EVENTS, NewsCharity(events))
    }

    private fun initAdapter(){
        binding.placeHolder.visibility = if (events.isEmpty()) View.VISIBLE else View.GONE
        adapter = EventsAdapter(events){}

        binding.rvEvents.adapter = adapter
        binding.rvEvents.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun subscribeSearchView(){
        searchViewListener?.let {
            it.debounce(EVENTS_DELAY_MLS, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { request -> onRequest(request.toString())}
        }
    }

    private fun onRequest(request: String){
        if (request.isEmpty()) {
            binding.placeHolder.visibility = View.VISIBLE
            binding.rvEvents.visibility = View.GONE
        } else {
            binding.rvEvents.visibility = View.VISIBLE
            binding.placeHolder.visibility = View.GONE

            Utils.getEventsRxJava(requireContext())
                .map {
                    it.news.filter { event ->
                        event.title.contains(request, true)
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { events ->
                    Log.e("Current thread is ", Thread.currentThread().name)
                    this.events = events
                    adapter?.updateEvents(events)
                }
        }
    }

    companion object{
        private const val EVENTS = "EVENTS"
        private const val EVENTS_DELAY_MLS = 500L
    }
}