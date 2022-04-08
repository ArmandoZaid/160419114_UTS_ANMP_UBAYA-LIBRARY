package com.ubaya.a160419114_uts_ubayalibrary.viewmodel

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
import com.ubaya.a160419114_uts_ubayalibrary.Model.Buku

class BukuDetailViewModel(application: Application) : AndroidViewModel(application) {
    val bukuLiveData = MutableLiveData<Buku>()

    val TAG = "detailTAG"
    private var queueDetail : RequestQueue? = null

    fun fetch(bukuID: String?){
        queueDetail = Volley.newRequestQueue(getApplication())
        Log.d("Show ID", bukuID.toString())
        val urlDetail = "http://192.168.1.4:8080/Armando/anmp_php/buku_detail.php?id=${bukuID}"
        val stringRequestDetail = StringRequest(
            Request.Method.GET, urlDetail, {
                val sType = object : TypeToken<Buku>() {}.type
                val resultDetail = Gson().fromJson<Buku>(it, sType)
                bukuLiveData.value = resultDetail
                Log.d("SHOW VOLLEY", it)
            }, {
                Log.d("SHOW VOLLEY", it.toString())
            }
        ).apply {
            tag = TAG
        }
        queueDetail?.add(stringRequestDetail)
    }

    override fun onCleared() {
        super.onCleared()
        queueDetail?.cancelAll(TAG)
    }

}