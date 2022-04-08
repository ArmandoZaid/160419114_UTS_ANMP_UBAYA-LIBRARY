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
import com.ubaya.a160419114_uts_ubayalibrary.Model.Ruang

class RuangDetailViewModel(application: Application) : AndroidViewModel(application) {
    val ruangLiveData = MutableLiveData<Ruang>()

    val TAG = "detailTag"
    private var queueRD: RequestQueue? = null

    fun fetchRD(ruangID: String?){
        queueRD = Volley.newRequestQueue(getApplication())
        Log.d("Show ID", ruangID.toString())

        val urlRD = "http://192.168.1.4:8080/Armando/anmp_php/ruang_detail.php?id=${ruangID}"
        val stringRequestRD = StringRequest(
            Request.Method.GET, urlRD, {
                val sType = object : TypeToken<Ruang>() {}.type
                val resultRD = Gson().fromJson<Ruang>(it, sType)
                ruangLiveData.value = resultRD
                Log.d("SHOW VOLLEY", it)
            }, {
                Log.d("SHOW VOLLEY", it.toString())
            }
        ).apply {
            tag = TAG
        }
        queueRD?.add(stringRequestRD)
    }

    override fun onCleared() {
        super.onCleared()
        queueRD?.cancelAll(TAG)
    }
}