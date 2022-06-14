package com.example.usuarios.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.usuarios.R
import com.example.usuarios.adapter.ListUserAdapter
import com.example.usuarios.data.User
import com.example.usuarios.databinding.FragmentHomeBinding
import com.example.usuarios.viewmodel.UserViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeFragment : Fragment(), HomeContract.View {
    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel
    private val presenter = HomePresenter()
    private var p = User(0,0,0,0,0, 0, 0, "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get()
        presenter.view = this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        setListernes()
        setRecycler()

        return view
    }

    fun setListernes(){
        binding.floatingActionButton.setOnClickListener{
            presenter.checkUser(p)
            dialogSorterNumberConstruction()
        }

        binding.textViewCleanList.setOnClickListener {
            dialogDeleteAllUsers()
        }
    }

    fun setRecycler(){
        viewModel.readAllData.observe(viewLifecycleOwner, Observer { lista ->
            binding.recyclerUsuarios.adapter = ListUserAdapter(lista)

            if (lista.isNotEmpty()){
                binding.textViewCleanList.visibility = View.VISIBLE
                binding.textViewEmpty.visibility = View.INVISIBLE
            } else{
                binding.textViewEmpty.visibility = View.VISIBLE
                binding.textViewCleanList.visibility = View.INVISIBLE
            }
        })
    }

    fun dialogDeleteAllUsers(){
        var dialogBuilder = MaterialAlertDialogBuilder(requireContext())
        var inflater = this.layoutInflater
        var dialogView = inflater.inflate(R.layout.delete_all, null)
        dialogBuilder.setView(dialogView)

        dialogBuilder
            .setTitle("Limpar lista")
            .setPositiveButton("Sim") { dialog, which ->
                viewModel.deleteAllUsers()
            }
            .setNegativeButton("Não"){ dialog, which ->

            }
            .show()
    }

    fun dialogSorterNumberConstruction(){
        var dialogBuilder = MaterialAlertDialogBuilder(requireContext())
        var inflater = this.layoutInflater
        var dialogView = inflater.inflate(R.layout.number_drawm_create_success, null)
        dialogBuilder.setView(dialogView)

        dialogBuilder
            .setTitle("Parabéns")
            .setPositiveButton("OK") { dialog, which ->
                // Respond to positive button press
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun addUser(user: User) {
        viewModel.addUser(user)
    }
}