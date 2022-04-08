package com.ubaya.a160419114_uts_ubayalibrary.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160419114_uts_ubayalibrary.R
import com.ubaya.a160419114_uts_ubayalibrary.util.loadImage
import com.ubaya.a160419114_uts_ubayalibrary.viewmodel.BukuDetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_buku_detail.*
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass.
 * Use the [BukuDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BukuDetailFragment : Fragment() {
    private lateinit var viewModel: BukuDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buku_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            arguments?.let {
                val bukuID = BukuDetailFragmentArgs.fromBundle(requireArguments()).bukuID
                viewModel = ViewModelProvider(this).get(BukuDetailViewModel::class.java)
                Log.d("Cek ID", bukuID.toString())
                viewModel.fetch(bukuID)

            }
    }

    private fun observeViewModel() {
        viewModel.bukuLiveData.observe(viewLifecycleOwner) {
            val buku = viewModel.bukuLiveData.value
            buku?.let {
                textNamaBukuDetail.setText(it.nama_buku)
                textAuthorDetail.setText(it.author_buku)
                textDeskripsi.setText(it.deskripsi_buku)
                textRatingDetail.setText(it.rating_buku)
                imageBukuDetail.loadImage(buku.gambar_buku, progressLoadingBukuDetail)
            }
        }
    }

}