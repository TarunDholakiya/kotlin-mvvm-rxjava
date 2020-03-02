package com.tarun.demo.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item(
    @SerializedName("amt.pledged")
    val amtpledged: Int,
    @SerializedName("blurb")
    val blurb: String,
    @SerializedName("by")
    val `by`: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("end.time")
    val endtime: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("num.backers")
    val numbackers: String,
    @SerializedName("percentage.funded")
    val percentagefunded: Int,
    @SerializedName("s.no")
    val sno: Int,
    @SerializedName("state")
    val state: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
) : Serializable