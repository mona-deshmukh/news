package com.example.newsapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.R
import kotlinx.android.synthetic.main.activity_all_news.*

class AllNewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_news)

        supportActionBar?.elevation = 2F
        val sectionsPagerAdapter = NewsViewPagerAdapter(supportFragmentManager)
        val frag1 = NewsListFragment.getAllNewsInstance()
        val frag2 = NewsListFragment.getScienceNewsInstance()

        sectionsPagerAdapter.add(frag1, "News")
        sectionsPagerAdapter.add(frag2, "Science")
        viewPager.adapter = sectionsPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}