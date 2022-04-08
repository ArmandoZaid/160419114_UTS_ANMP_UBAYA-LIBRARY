package com.ubaya.a160419114_uts_ubayalibrary.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160419114_uts_ubayalibrary.R
import com.ubaya.a160419114_uts_ubayalibrary.util.loadImage
import com.ubaya.a160419114_uts_ubayalibrary.viewmodel.BukuDetailViewModel
import com.ubaya.a160419114_uts_ubayalibrary.viewmodel.RuangDetailViewModel
import kotlinx.android.synthetic.main.fragment_buku_detail.*
import kotlinx.android.synthetic.main.fragment_ruang_detail.*

class RuangDetailFragment : Fragment() {
    private lateinit var viewModel: RuangDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ruang_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val ruangID = RuangDetailFragmentArgs.fromBundle(requireArguments()).ruangID
            viewModel = ViewModelProvider(this).get(RuangDetailViewModel::class.java)
            Log.d("Cek ID", ruangID.toString())
            viewModel.fetchRD(ruangID)

        }
    }

    private fun observeViewModel() {
        viewModel.ruangLiveData.observe(viewLifecycleOwner) {
            val ruang = viewModel.ruangLiveData.value
            ruang?.let {
                textNamaRuangDetail.setText(it.nama)
                textKapasitasRuangDetail.setText(it.Kapasitas)
                imageViewRuangDetail.loadImage(ruang.gambar, progressBarRuangDetail)
            }
        }
    }
}