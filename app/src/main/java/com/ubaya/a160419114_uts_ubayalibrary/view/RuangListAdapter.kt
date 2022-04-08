package com.ubaya.a160419114_uts_ubayalibrary.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419114_uts_ubayalibrary.Model.Ruang
import com.ubaya.a160419114_uts_ubayalibrary.R
import com.ubaya.a160419114_uts_ubayalibrary.util.loadImage
import kotlinx.android.synthetic.main.ruang_list_item.view.*

class RuangListAdapter(val ruangList: ArrayList<Ruang>) : RecyclerView.Adapter<RuangListAdapter.RuangViewHolder>(){
    class RuangViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RuangViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.ruang_list_item , parent, false)
        return RuangViewHolder(view)
    }

    override fun onBindViewHolder(holder: RuangViewHolder, position: Int) {
        val ruang  = ruangList[position]
        with(holder.view){
            textNamaRuang.text = ruang.nama

            buttonDetailRuang.setOnClickListener {
                val act = ruang.idruang_pinjam?.let{ id ->
                    RuangFragmentDirections.actionRuangDetail(id)
                }
                if ( act != null) Navigation.findNavController(it).navigate(act)

            }
            imageRuang.loadImage(ruang.gambar, progressBarRuang)
        }
    }

    override fun getItemCount() = ruangList.size

    fun updateRuangList(newRuangList : List<Ruang>){
        ruangList.clear()
        ruangList.addAll(newRuangList)
        notifyDataSetChanged()
    }
}