package com.ubaya.a160419114_uts_ubayalibrary.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419114_uts_ubayalibrary.Model.Buku
import com.ubaya.a160419114_uts_ubayalibrary.R
import com.ubaya.a160419114_uts_ubayalibrary.util.loadImage
import kotlinx.android.synthetic.main.rekomen_list_item.view.*

class BukuListAdapter(val bukuList: ArrayList<Buku>) : RecyclerView.Adapter<BukuListAdapter.BukuViewHolder>(){
    class BukuViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.rekomen_list_item, parent, false)
        return BukuViewHolder(view)
    }

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
        val buku = bukuList[position]
        with(holder.view){
            textNamaRekomen.text = buku.nama_buku

            buttonDetailRekomen.setOnClickListener {
                val act = buku.idbuku?.let{ id ->
                    RekomenFragmentDirections.actionRekomenBukuDetail(id)
                }
                if ( act != null) Navigation.findNavController(it).navigate(act)

            }
            ImageBukuRekomen.loadImage(buku.gambar_buku, progressBarRekomen)
        }
    }

    override fun getItemCount() = bukuList.size

    fun updateBukuList(newBukuList : List<Buku>){
        bukuList.clear()
        bukuList.addAll(newBukuList)
        notifyDataSetChanged()
    }
}