package com.example.msudesh_kumar.puneeth_task.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.msudesh_kumar.puneeth_task.fragment.TodayFragment

class TabFragmentAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {
    private var mFragmentList : ArrayList<Fragment> = ArrayList()
    private var mFragmentTitleList : ArrayList<String> = ArrayList()

    override fun getItem(p0: Int): Fragment {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return mFragmentList[p0]
    }

    override fun getCount(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return mFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        //return super.getPageTitle(position)
        return mFragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

}