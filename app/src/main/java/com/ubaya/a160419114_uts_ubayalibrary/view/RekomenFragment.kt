package com.ubaya.a160419114_uts_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ubaya.a160419114_uts_ubayalibrary.R
import com.ubaya.a160419114_uts_ubayalibrary.viewmodel.BukuViewModel
import kotlinx.android.synthetic.main.fragment_rekomen.*

/**
 * A simple [Fragment] subclass.
 * Use the [RekomenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RekomenFragment : Fragment() {

    private lateinit var viewModel :BukuViewModel
    private val bukuListAdapter = BukuListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rekomen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(BukuViewModel::class.java)
        viewModel.refresh()

        recViewRekomen.layoutManager = LinearLayoutManager(context)
        recViewRekomen.adapter = bukuListAdapter

        observeViewModel()

        RekomenRefreshLayout.setOnRefreshListener {
            recViewRekomen.visibility = View.GONE
            textErrorRekomen.visibility = View.GONE
            progressLoadRekomen.visibility = View.VISIBLE
            viewModel.refresh()
            RekomenRefreshLayout.isRefreshing =false
        }
    }

    private fun observeViewModel() {
        viewModel.bukusLiveData.observe(viewLifecycleOwner) {
            bukuListAdapter.updateBukuList(it)
        }
        viewModel.bukuLoadErrorLD.observe(viewLifecycleOwner) {
            textErrorRekomen.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if (it) {
                recViewRekomen.visibility = View.GONE
                progressLoadRekomen.visibility = View.VISIBLE
            } else {
                recViewRekomen.visibility = View.VISIBLE
                progressLoadRekomen.visibility = View.GONE
            }
        }
    }

}