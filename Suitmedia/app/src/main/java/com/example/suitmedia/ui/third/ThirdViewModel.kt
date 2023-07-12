package com.example.suitmedia.ui.third

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.suitmedia.data.UserRepository
import com.example.suitmedia.di.Injection
import com.example.suitmedia.network.DataItem

class ThirdViewModel(userRepository: UserRepository): ViewModel() {

    val user: LiveData<PagingData<DataItem>> =
        userRepository.getUser().cachedIn(viewModelScope)

}

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ThirdViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}