package com.example.roomlist.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle

import com.example.libdb.model.entity.Empresa

import com.example.roomlist.R
import com.example.roomlist.app.UserApplication
import com.example.roomlist.list.placeholder.PlaceholderContent
import com.example.roomlist.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.roomlist.databinding.FragmentUserBinding
import com.example.roomlist.databinding.FragmentUserListBinding
/**
 * A fragment representing a list of Items.
 */
class UserFragment : Fragment() {

    private var columnCount = 1

    private val viewModel: UserViewModel by viewModels {
        UserViewModel.UserViewModelFactory((
                this@UserFragment.requireActivity().application as UserApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyUserRecyclerViewAdapter(listOf<Empresa>())
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter=FragmentUserListBinding.bind(view).list.adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allUsers.collectLatest { empresas->
                    empresas?.let {
                        FragmentUserListBinding.bind(view).list.adapter=MyUserRecyclerViewAdapter(empresas)
                        //Snackbar.make(binding.buttonSecond,it, Snackbar.LENGTH_LONG).show()
                    }

                }

            }
        }

    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}