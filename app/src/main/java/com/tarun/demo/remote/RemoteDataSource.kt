package com.tarun.demo.remote

import com.tarun.demo.model.Item
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.*

object RemoteDataSource {
    private val apiServices = ApiClient.getApiService()

    fun getItemList(): Observable<Response<ArrayList<Item>>> {
        return apiServices.getItemList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}