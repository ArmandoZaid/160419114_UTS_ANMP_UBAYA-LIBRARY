package com.ubaya.a160419114_uts_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ubaya.a160419114_uts_ubayalibrary.R
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonRuang.setOnClickListener {
            val act = MainFragmentDirections.actionRuang()
            Navigation.findNavController(it).navigate(act)
        }

        buttonInfo.setOnClickListener {
            val act = MainFragmentDirections.actionInfoUbaya()
            Navigation.findNavController(it).navigate(act)
        }

        buttonFAQ.setOnClickListener {
            val act = MainFragmentDirections.actionFAQ()
            Navigation.findNavController(it).navigate(act)
        }
    }


}