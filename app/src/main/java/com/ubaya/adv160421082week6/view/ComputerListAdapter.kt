package com.ubaya.adv160421082week6.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.adv160421082week6.databinding.ComputerItemBinding
import com.ubaya.adv160421082week6.model.Computer

class ComputerListAdapter (val ComputerList:ArrayList<Computer>)
    : RecyclerView.Adapter<ComputerListAdapter.ComputerViewHolder>()
{
        class ComputerViewHolder(var binding: ComputerItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComputerViewHolder {
        val binding = ComputerItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ComputerViewHolder(binding)//cuma nama variabel. nentuin binding ke studentlistitem
    }

    override fun getItemCount(): Int {
        return ComputerList.size
    }
    override fun onBindViewHolder(holder: ComputerViewHolder, position: Int) {
        holder.binding.txtName.text = ComputerList[position].name
        holder.binding.txtBrand.text = ComputerList[position].brand
        holder.binding.txtPrice.text = ComputerList[position].price

//        holder.binding.btnDetail.setOnClickListener {
//            val action = StudentListFragmentDirections.actionStudentDetail()
//            Navigation.findNavController(it).navigate(action)
//        }
        //function untuk tiap proses yang akan dimasukkan ke dalam list item. Masukkin per itemnya datanya ke textnya gitu.
    }
    fun updateComputerList(newComputerList: ArrayList<Computer>) {
        ComputerList.clear()
        ComputerList.addAll(newComputerList)
        notifyDataSetChanged()
    }
}
