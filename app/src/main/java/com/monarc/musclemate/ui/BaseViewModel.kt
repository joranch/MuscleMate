package com.monarc.musclemate.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


open class BaseViewModel : ViewModel() {

    private val _showSnackbar = MutableSharedFlow<String>()
    val showSnackbar = _showSnackbar.asSharedFlow()

    private val _showToast = MutableSharedFlow<String>()
    val showToast = _showToast.asSharedFlow()

    private val _showError = MutableSharedFlow<String>()
    val showError = _showError.asSharedFlow()

    fun showSnackbarMessage(message: String){
        viewModelScope.launch {
            _showSnackbar.emit(message)
        }
    }

    fun showToastMessage(message: String){
        viewModelScope.launch {
            _showToast.emit(message)
        }
    }

    fun showErrorMessage(message: String){
        viewModelScope.launch {
            _showError.emit(message)
        }
    }
}