package com.example.roomlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.db.model.entity.User
import com.example.libdb.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import com.example.libdb.repository.EmpresaRepository
import com.example.libdb.model.entity.Empresa

class UserViewModel(private val repository: EmpresaRepository):ViewModel() {



    private val _info= MutableStateFlow<String?>(null)
    val info=_info.asStateFlow()


    val allUsers: Flow<List<Empresa>> = repository.allUsers

    fun insert(empresa: Empresa) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(empresa)
    }

    class UserViewModelFactory(private val repository: EmpresaRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


    companion object{
        private const val TAG="UserViewModel"
    }

}