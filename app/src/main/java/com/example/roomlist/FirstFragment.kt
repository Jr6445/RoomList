package com.example.roomlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.db.model.entity.User
import com.example.roomlist.app.UserApplication
import com.example.roomlist.databinding.FragmentFirstBinding
import com.example.roomlist.viewmodel.UserViewModel
import com.google.android.material.textfield.TextInputLayout
import com.example.libdb.model.entity.Empresa

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {



    private val viewModel: UserViewModel by viewModels {
        UserViewModel.UserViewModelFactory(
            (this@FirstFragment.requireActivity().application as UserApplication).repository
        )
    }

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.buttonInsert.setOnClickListener {
            val nombre: String = binding.nombre.text.toString()
            val direccion: String = binding.dire.text.toString()
            val email: String = binding.email.text.toString()
            val tele: String = binding.tele.text.toString()
            val url: String = binding.url.text.toString()


            viewModel.insert(Empresa(0,nombre=nombre,direccion=direccion, email = email, telefono = tele, urlImagen = url))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}