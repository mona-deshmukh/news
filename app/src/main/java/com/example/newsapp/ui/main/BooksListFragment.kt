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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.BooksListAdapter
import com.example.newsapp.viewmodel.BooksListViewModel
import kotlinx.android.synthetic.main.fragment_layout.*

class BooksListFragment : Fragment() {
    private lateinit var viewModel: BooksListViewModel
    private lateinit var booksAdapter: BooksListAdapter

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
        viewModel = ViewModelProvider(this).get(BooksListViewModel::class.java)
        viewModel.getScienceBooks()

        booksAdapter = BooksListAdapter(ArrayList())
        booksAdapter.onImageClick = {
            val startWebView = Intent(activity, WebViewActivity::class.java).apply {
                putExtra(INTENT_URL, it?.replace("http:", "https:"))
            }
            startActivity(startWebView)
        }

        recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = booksAdapter
        }
        booksAdapter.notifyDataSetChanged()

        viewModel.booksList.observe(viewLifecycleOwner, Observer {
            if (it == null)
                return@Observer

            booksAdapter.dataSource = it
            booksAdapter.notifyDataSetChanged()
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
        fun getAllBooksInstance(): BooksListFragment {
            return BooksListFragment().apply {
                arguments = Bundle().apply {
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}