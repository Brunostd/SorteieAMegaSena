package com.denysorteie.usuarios.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.denysorteie.usuarios.R
import com.denysorteie.usuarios.adapter.ListUserAdapter
import com.denysorteie.usuarios.data.User
import com.denysorteie.usuarios.databinding.FragmentHomeBinding
import com.denysorteie.usuarios.viewmodel.UserViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), HomeContract.View {
    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: UserViewModel by viewModel()
    private val presenter = HomePresenter()
    private var p = User(0,0,0,0,0, 0, 0, "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            presenter.checkList(lista)
        })
    }
    override fun addUser(user: User) {
        viewModel.addUser(user)
    }

    override fun showCleanList(visibility: Int) {
        binding.textViewCleanList.visibility = visibility
    }

    override fun showListEmpty(visibility: Int) {
        binding.textViewEmpty.visibility = visibility
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
}