package com.example.msudesh_kumar.puneeth_task.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.example.msudesh_kumar.puneeth_task.R
import com.example.msudesh_kumar.puneeth_task.adapter.TabFragmentAdapter
import com.example.msudesh_kumar.puneeth_task.fragment.LaterFragment
import com.example.msudesh_kumar.puneeth_task.fragment.TodayFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewPager : ViewPager
    private lateinit var mainActivityTabLayout : TabLayout
    private lateinit var tabFragmentAdapter: TabFragmentAdapter
    val fragment1 = TodayFragment()
    val fragment2 = LaterFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        mainActivityViewPager.adapter = tabFragmentAdapter

    }

    private fun init()
    {
        mainActivityViewPager = findViewById(R.id.activity_main_pager)
        mainActivityTabLayout = findViewById(R.id.activity_main_tabLayout)
        mainActivityTabLayout.setupWithViewPager(mainActivityViewPager)
        tabFragmentAdapter = TabFragmentAdapter(supportFragmentManager)
        fragment1.mContext = applicationContext
        fragment2.mContext = applicationContext
        tabFragmentAdapter.addFragment(fragment1, fragment1.title)
        tabFragmentAdapter.addFragment(fragment2, fragment2.title)
    }
}
