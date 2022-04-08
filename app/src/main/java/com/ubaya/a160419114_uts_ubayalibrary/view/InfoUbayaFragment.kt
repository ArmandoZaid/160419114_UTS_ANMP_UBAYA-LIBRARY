package com.ubaya.a160419114_uts_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ubaya.a160419114_uts_ubayalibrary.R
import kotlinx.android.synthetic.main.fragment_info_ubaya.*
import kotlinx.android.synthetic.main.fragment_main.*


class InfoUbayaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_ubaya, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonInfoKembali.setOnClickListener {
            val act = InfoUbayaFragmentDirections.actionKembaliInfoHome()
            Navigation.findNavController(it).navigate(act)
        }
    }
}