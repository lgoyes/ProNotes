package com.lamadridblandongoyes.pronotes.home

import android.os.Bundle
import android.widget.Toast
import com.lamadridblandongoyes.pronotes.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class HomeActivity: DaggerAppCompatActivity(), HomeContract.View {

    @Inject
    lateinit var presenter: HomeContract.Presenter

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        lifecycle.addObserver(presenter)
        presenter.bind(this)
    }
}