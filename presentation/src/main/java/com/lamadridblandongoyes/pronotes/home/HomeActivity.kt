package com.lamadridblandongoyes.pronotes.home

import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.lamadridblandongoyes.pronotes.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity: DaggerAppCompatActivity(), HomeContract.View {

    @Inject
    lateinit var presenter: HomeContract.Presenter

    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bindViews()
        setupViewPager()

        lifecycle.addObserver(presenter)
        presenter.bind(this)
    }

    private fun bindViews() {
        this.viewPager = home_container
        this.tabLayout = tabs
    }

    private fun setupViewPager() {
        this.viewPager.adapter = PagerAdapter(supportFragmentManager)
        this.tabLayout.setupWithViewPager(this.viewPager)
    }
}