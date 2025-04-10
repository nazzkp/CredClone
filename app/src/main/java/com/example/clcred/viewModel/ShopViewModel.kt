package com.example.clcred.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.clcred.model.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: DataRepository,
) : ViewModel() {}