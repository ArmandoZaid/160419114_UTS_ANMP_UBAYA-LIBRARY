package com.ubaya.a160419114_uts_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160419114_uts_ubayalibrary.R
import com.ubaya.a160419114_uts_ubayalibrary.viewmodel.BukuViewModel
import com.ubaya.a160419114_uts_ubayalibrary.viewmodel.RuangViewModel
import kotlinx.android.synthetic.main.fragment_rekomen.*
import kotlinx.android.synthetic.main.fragment_ruang.*

/**
 * A simple [Fragment] subclass.
 * Use the [RuangFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RuangFragment : Fragment() {

    private lateinit var viewModel : RuangViewModel
    private val ruangListAdapter = RuangListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ruang, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(RuangViewModel::class.java)
        viewModel.refresh()

        recViewRuang.layoutManager = LinearLayoutManager(context)
        recViewRuang.adapter = ruangListAdapter

        observeViewModel()

        RuangRefreshLayout.setOnRefreshListener {
            recViewRuang.visibility = View.GONE
            textErrorRuang.visibility = View.GONE
            progressLoadRuang.visibility = View.VISIBLE
            viewModel.refresh()
            RuangRefreshLayout.isRefreshing =false
        }

    }

    private fun observeViewModel(){
        viewModel.ruangsLiveData.observe(viewLifecycleOwner){
            ruangListAdapter.updateRuangList(it)
        }
        viewModel.ruangLoadErrorLD.observe(viewLifecycleOwner) {
            textErrorRuang.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.ruangloadingLiveData.observe(viewLifecycleOwner){
            if (it) {
                recViewRuang.visibility = View.GONE
                progressLoadRuang.visibility = View.VISIBLE
            } else {
                recViewRuang.visibility = View.VISIBLE
                progressLoadRuang.visibility = View.GONE
            }
        }
    }
}