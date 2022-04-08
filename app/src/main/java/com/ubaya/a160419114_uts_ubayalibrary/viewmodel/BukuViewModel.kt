package com.ubaya.a160419114_uts_ubayalibrary.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419114_uts_ubayalibrary.Model.Buku

class BukuViewModel(application: Application) : AndroidViewModel(application) {
    val bukusLiveData = MutableLiveData<ArrayList<Buku>>()
    val bukuLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()

    val TAG = "volleyTAG"
    private var q: RequestQueue? = null

    fun refresh(){
        bukuLoadErrorLD.value = false
        loadingLiveData.value = true

        q = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.1.4:8080/Armando/anmp_php/buku.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url,{
                val sType = object : TypeToken<ArrayList<Buku>>() {}.type
                val result = Gson().fromJson<ArrayList<Buku>>(it, sType)
                Log.d("showvoley", result.toString())

                bukusLiveData.value = result
                loadingLiveData.value = false

            },{

                bukuLoadErrorLD.value = true
                loadingLiveData.value = false
                Log.d("showvoley", it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        q?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        q?.cancelAll(TAG)
    }
}