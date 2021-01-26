package com.example.test_app.ui

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.test_app.R
import com.example.test_app.entity.QueryResult
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*

/**
 * An activity representing a list of Items.
 * The activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing item details.
 */
class ItemListActivity : AppCompatActivity() {

    private val viewModel: ItemListViewModel by lazy { ViewModelProvider(this)[ItemListViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        setupViewModel()
        setupRecyclerView()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getQueryProducts(this@ItemListActivity, query)
                progressBar.visibility = View.VISIBLE
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    private fun setupViewModel() {
        viewModel.mutableResponseLiveData.observe(this, {
            progressBar.visibility = View.GONE
            updateRecyclerView(it)
        })
        viewModel.mutableErrorLiveData.observe(this, {
            progressBar.visibility = View.GONE
            Snackbar.make(window.decorView.rootView, it, Snackbar.LENGTH_SHORT).show()
        })
    }

    private fun setupRecyclerView() {
        item_list.adapter = SimpleItemRecyclerViewAdapter(listOf(), 0)
    }

    private fun updateRecyclerView(result: QueryResult) {
        (item_list.adapter as SimpleItemRecyclerViewAdapter).values = result.results ?: listOf()
        (item_list.adapter as SimpleItemRecyclerViewAdapter).primaryResults = result.paging?.primary_results ?: 0
        (item_list.adapter as SimpleItemRecyclerViewAdapter).notifyDataSetChanged()
    }
}