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

class RuangViewModel(application: Application) :AndroidViewModel(application){
    val ruangsLiveData = MutableLiveData<ArrayList<Ruang>>()
    val ruangLoadErrorLD = MutableLiveData<Boolean>()
    val ruangloadingLiveData = MutableLiveData<Boolean>()

    val TAG = "volleyTAG"
    private var qRuang: RequestQueue? = null


    fun refresh(){
        ruangLoadErrorLD.value = false
        ruangloadingLiveData.value = true

        qRuang = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.1.4:8080/Armando/anmp_php/ruang.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url,{
                val sType = object : TypeToken<ArrayList<Ruang>>() {}.type
                val result = Gson().fromJson<ArrayList<Ruang>>(it, sType)
                Log.d("showvoley", result.toString())

                ruangsLiveData.value = result
                ruangloadingLiveData.value = false

            },{

                ruangLoadErrorLD.value = true
                ruangloadingLiveData.value = false
                Log.d("showvoley", it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        qRuang?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        qRuang?.cancelAll(TAG)
    }
}
