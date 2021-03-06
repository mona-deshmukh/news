package com.example.newsapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsListAdapter
import com.example.newsapp.viewmodel.NewsListViewModel
import kotlinx.android.synthetic.main.fragment_layout.*

class NewsListFragment : Fragment() {
    private lateinit var viewModel: NewsListViewModel
    private lateinit var newsAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       
        init()
    }
    private fun init() {
        viewModel = ViewModelProvider(this).get(NewsListViewModel::class.java)
        viewModel.getTopHeadlines()

        newsAdapter = NewsListAdapter(ArrayList())
        newsAdapter.onImageClick = {
            val startWebView = Intent(activity, WebViewActivity::class.java).apply {
                putExtra(INTENT_URL, it.url?.replace("http:", "https:"))
            }
            startActivity(startWebView)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = newsAdapter
        }
        newsAdapter.notifyDataSetChanged()

        viewModel.topHeadlines.observe(viewLifecycleOwner, Observer {
            if (it == null)
                return@Observer

            newsAdapter.dataSource = it
            newsAdapter.notifyDataSetChanged()
            progressView.visibility = View.GONE
            progressView.stop()
        })
        viewModel.entertainmentNews.observe(viewLifecycleOwner, Observer {
            if (it == null)
                return@Observer

            newsAdapter.dataSource = it
            newsAdapter.notifyDataSetChanged()
            progressView.visibility = View.GONE
            progressView.stop()
        })

        viewModel.topHeadlines.observe(viewLifecycleOwner, Observer {
            if (it == null)
                return@Observer

            newsAdapter.dataSource = it
            newsAdapter.notifyDataSetChanged()
            progressView.visibility = View.GONE
            progressView.stop()
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            if (it == null)
                return@Observer
            progressView.visibility = View.GONE
            progressView.stop()

            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
    }

    companion object {
        fun getAllNewsInstance(): NewsListFragment {
            return NewsListFragment().apply {
                arguments = Bundle().apply {
                }
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()

    }
}