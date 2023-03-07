package com.example.skincancer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.skincancer.adapter.SectionsPagerAdapter
import com.example.skincancer.fragments.ListSkinFragment
import com.example.skincancer.viewmodel.SkinViewModel
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() , ListSkinFragment.OnListFragmentInteractionListener {


    private lateinit var model: SkinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewpager: ViewPager = findViewById(R.id.view_pager)
        viewpager.adapter = myPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewpager)
        model = SkinViewModel.getInstance(this)
    }

    override fun onListFragmentInteraction(item: SkinCancerVO) {
        model.setSelectedSkinCancer(item)
    }

}