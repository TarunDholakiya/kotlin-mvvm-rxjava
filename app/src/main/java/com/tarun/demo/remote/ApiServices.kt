package com.tarun.demo.remote

import com.tarun.demo.model.Item
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import java.util.*

interface ApiServices {

    @GET("json/api.json")
    fun getItemList(): Observable<Response<ArrayList<Item>>>
}