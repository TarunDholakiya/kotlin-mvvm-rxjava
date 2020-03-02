package com.tarun.demo.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tarun.demo.R
import com.tarun.demo.adapter.ItemAdapter
import com.tarun.demo.model.Item
import com.tarun.demo.utils.viewModelFactory
import com.tarun.demo.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val itemViewModel by lazy {
        ViewModelProviders
            .of(this, viewModelFactory { ItemViewModel() })
            .get(ItemViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvItems.layoutManager = LinearLayoutManager(this)
        itemViewModel.getItemList()
        observeItems()
    }

    private fun observeItems() {
        itemViewModel.items.observe(this, Observer {
            if (it.isNotEmpty()) {
                it.sortBy(Item::title)
                rvItems.adapter = ItemAdapter(it)
            } else {
                Toast.makeText(this, getString(R.string.no_results_found), Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.clear()
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
