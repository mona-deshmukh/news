package com.example.newsapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.R
import com.example.newsapp.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_all_news.*

const val INTENT_URL = "web_url"
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_news)

        supportActionBar?.elevation = 2F
        val sectionsPagerAdapter =
            ViewPagerAdapter(
                supportFragmentManager
            )
        val newsFragment = NewsListFragment.getAllNewsInstance()
        val booksFragment = BooksListFragment.getAllBooksInstance()

        sectionsPagerAdapter.add(newsFragment, "News")
        sectionsPagerAdapter.add(booksFragment, "Books")
        viewPager.adapter = sectionsPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}