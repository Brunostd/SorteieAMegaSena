package com.example.usuarios.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.usuarios.R
import com.example.usuarios.data.User
import com.example.usuarios.databinding.ListaUsuariosBinding
import com.example.usuarios.ui.fragments.home.HomeFragmentDirections

class ListUserAdapter(var listaUser: List<User>): RecyclerView.Adapter<ListUserAdapter.MyViewHolder>() {
    class MyViewHolder(private val itemBinding: ListaUsuariosBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(user: User){
            itemBinding.run {
                var aux = user.numberOne.toString()
                aux += " / " + user.numberTwo.toString()
                aux += " / " + user.numberThree.toString()
                aux += " / " + user.numberFour.toString()
                aux += " / " + user.numberFive.toString()
                aux += " / " + user.numberSix.toString()
                itemBinding.textViewId.text = aux
                itemBinding.textViewDate.text = user.date

                itemBinding.cardAposta.setOnClickListener {
                    val action = HomeFragmentDirections.actionHomeFragmentToDeleteFragment(user, aux)
                    itemBinding.root.findNavController().navigate(action)
                }
            }
        }
        companion object{
            fun create(parent: ViewGroup): MyViewHolder{
                val itemBinding = ListaUsuariosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MyViewHolder(itemBinding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listaUser[position])
    }

    override fun getItemCount(): Int = listaUser.size
}