package com.github.mishkaff89.practicumdev.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mishkaff89.practicumdev.databinding.FragmentNewsDetailBinding
import com.github.mishkaff89.practicumdev.news.helpers.Constants
import com.github.mishkaff89.practicumdev.news.helpers.Utils


class NewsDetailFragment : Fragment() {
    private lateinit var binding: FragmentNewsDetailBinding

    private var newsId: Int? = null

    private val currentNews by lazy {
        val news = Utils.getNews(requireContext().applicationContext)
        news.news.find { news -> news.id == newsId }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        arguments?.let {
            newsId = it.getInt(Constants.NEWS_ID)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() = with(binding) {
            currentNews?.let { news ->
                toolbar.title = news.title
                tvNewsTitle.text = news.title
                tvEndDate.text = Utils.dateFormat(news.date)
                tvAddress.text = news.address
                tvEventDescription.text = news.description
                var phoneNumbers = ""
                for (i in 0 until news.phoneNumbers.count()) {
                    if (i == news.phoneNumbers.count() - 1) {
                        phoneNumbers += news.phoneNumbers[i]
                    } else {
                        phoneNumbers += news.phoneNumbers[i] + "\n"
                    }
                }
                tvPhoneNumber.text = phoneNumbers
                if (news.membersCount - 5 > 0) {
                    tvMembersCount.text = "+ ${news.membersCount - 5}"
                }
            }

            toolbar.setNavigationOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

    }


}