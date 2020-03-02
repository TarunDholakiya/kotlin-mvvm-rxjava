package com.tarun.demo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tarun.demo.R
import com.tarun.demo.adapter.ItemAdapter
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
                rvItems.adapter = ItemAdapter(this,it)
            } else {

            }
        })
    }
}
