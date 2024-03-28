package com.ubaya.adv160421082week6.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.adv160421082week6.model.Computer

class ListViewModel (application: Application) : AndroidViewModel(application){
    val computerLD = MutableLiveData<ArrayList<Computer>>()
    val computerLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        loadingLD.value = true
        computerLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://icfubaya2023.com/computer" //ga bisa buka json nya pakai localhost.

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Computer>>() { }.type
                val result = Gson().fromJson<List<Computer>>(it, sType)
                computerLD.value = result as ArrayList<Computer>?
                loadingLD.value = false

                Log.d("showvoley", result.toString())

            },
            {
                Log.d("showvoley", it.toString())
                computerLoadErrorLD.value = false
                loadingLD.value = false
            })

        stringRequest.tag = TAG // kasi identitas ke string request, objek yang akan dijalankan sama queue buat jalanin di server.
        queue?.add(stringRequest)

        loadingLD.value = false
        computerLoadErrorLD.value = false
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }


}