package com.example.compose.mvvm.ui.feature.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.mvvm.model.data.DishesMenuRemoteSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DishesCategoriesViewModel @Inject constructor(private val remoteSource: DishesMenuRemoteSource) :
    ViewModel() {

    var state by mutableStateOf(
        DishesCategoriesContract.State(
            categories = listOf(),
            isLoading = true
        )
    )
        private set

    var effects = Channel<DishesCategoriesContract.Effect>(UNLIMITED)
        private set

    init {
        viewModelScope.launch { getDishesCategories() }
    }

    private suspend fun getDishesCategories() {
        val categories = remoteSource.getDishesCategories()
        viewModelScope.launch {
            state = state.copy(categories = categories, isLoading = false)
            effects.send(DishesCategoriesContract.Effect.DataWasLoaded)
        }
    }
}



