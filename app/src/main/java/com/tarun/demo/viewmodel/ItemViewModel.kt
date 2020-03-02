package com.tarun.demo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tarun.demo.model.Item
import com.tarun.demo.remote.RemoteDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import java.util.*

class ItemViewModel : ViewModel() {
    val TAG = ItemViewModel::class.java.simpleName
    val items = MutableLiveData<ArrayList<Item>>()
    val compositeDisposable = CompositeDisposable()

    fun getItemList() {
        compositeDisposable.add(
            RemoteDataSource.getItemList().subscribe(Consumer {
                if (it.isSuccessful) {
                    Log.i("Success full", " :API call")
                }
            }, Consumer {
                    Log.e("ERROR :: ", "API parsing ")
            })
        )
    }
}