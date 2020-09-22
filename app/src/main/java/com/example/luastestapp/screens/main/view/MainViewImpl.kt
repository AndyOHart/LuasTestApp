package com.example.luastestapp.screens.main.view

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.luastestapp.R
import com.example.luastestapp.model.Tram
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.synthetic.main.activity_main.view.*
import javax.inject.Inject

class MainViewImpl @Inject constructor(context: Context) : FrameLayout(context),
    MainView {

    private var tramListAdapter: TramListAdapter = TramListAdapter(ArrayList())

    init {
        View.inflate(context, R.layout.activity_main, this)
        setupRecyclerView()
    }

    override fun showLoading() {
        progressBar.visibility = VISIBLE
        tramsRecyclerView.visibility = GONE
        noTramsAvailableImageView.visibility = GONE
        noTramsAvailableTextView.visibility = GONE
    }

    override fun hideLoading() {
        progressBar.visibility = GONE
    }

    override fun observeRefreshButtonClick(): Observable<Unit> {
        return refreshFab.clicks()
    }

    override fun showSnackBar(@StringRes textRes: Int) {
        Snackbar.make(this, context.getString(textRes), Snackbar.LENGTH_SHORT).show()
    }

    override fun showNoTramsAvailable(areTramsAvailable: Boolean) {
        tramsRecyclerView.visibility = if (areTramsAvailable) VISIBLE else GONE
        noTramsAvailableTextView.visibility = if (areTramsAvailable) GONE else VISIBLE
        noTramsAvailableImageView.visibility = if (areTramsAvailable) GONE else VISIBLE
    }

    override fun setTitle(stopName: String, direction: String, message: String) {
        toolbar.title = context.getString(R.string.toolbar_title, stopName, direction)
        toolbar.subtitle = message
    }

    override fun updateRecyclerView(tramList: List<Tram>) {
        tramListAdapter.updateTramList(tramList)
    }

    private fun setupRecyclerView() {
        tramsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tramListAdapter
        }
    }
}