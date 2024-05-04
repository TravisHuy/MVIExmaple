package com.nhathuy.mviexample.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nhathuy.mviexample.data.api.ApiHelper
import com.nhathuy.mviexample.data.repository.MainRepository
import com.nhathuy.mviexample.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        return super.create(modelClass)
    }
}