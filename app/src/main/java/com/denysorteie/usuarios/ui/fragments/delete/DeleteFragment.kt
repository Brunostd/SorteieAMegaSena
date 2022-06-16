package com.denysorteie.usuarios.ui.fragments.delete

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.denysorteie.usuarios.R
import com.denysorteie.usuarios.data.User
import com.denysorteie.usuarios.databinding.FragmentDeleteBinding
import com.denysorteie.usuarios.viewmodel.UserViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeleteFragment : Fragment() {
    private var _binding: FragmentDeleteBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: DeleteFragmentArgs by navArgs()
    private val viewModel: UserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeleteBinding.inflate(inflater, container, false)
        val view = binding.root

        setView()
        setListernes()

        return view
    }

    fun setView(){
        binding.textViewNumberDelete.text = args.numberDelete
        binding.textViewDateDelete.text = args.userDelete?.date
    }

    fun setListernes(){
        binding.backDelete.setOnClickListener {
            findNavController().navigate(R.id.action_deleteFragment_to_homeFragment)
        }

        binding.buttonDeleteUser.setOnClickListener {
            args.userDelete?.let {
                dialogDeleteUser(it)
            }
        }
    }

    fun dialogDeleteUser(user: User){
        var dialogBuilder = MaterialAlertDialogBuilder(requireContext())
        var inflater = this.layoutInflater
        var dialogView = inflater.inflate(R.layout.delete_play, null)
        dialogBuilder.setView(dialogView)

        dialogBuilder
            .setTitle("Excluir jogada")
            .setPositiveButton("Sim") { dialog, which ->
                viewModel.deleteUser(user)
                findNavController().navigate(R.id.action_deleteFragment_to_homeFragment)
            }
            .setNegativeButton("Não"){ dialog, which ->

            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}