package com.tarun.demo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.tarun.demo.model.Item
import com.tarun.demo.remote.RemoteDataSource
import java.util.*

class ItemViewModel : BaseViewModel() {
    val TAG = ItemViewModel::class.java.simpleName
    val items = MutableLiveData<ArrayList<Item>>()

    fun getItemList() {
        compositeDisposable.add(
            RemoteDataSource.getItemList().subscribe({
                if (it.isSuccessful) {
                    items.value = it.body()
                } else {
                    initializeApiError(it)
                }
            }, { initializeIOError(it) })
        )
    }
}