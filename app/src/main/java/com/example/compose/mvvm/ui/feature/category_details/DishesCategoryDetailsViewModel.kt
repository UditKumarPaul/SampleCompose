package com.example.compose.mvvm.ui.feature.category_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.example.compose.mvvm.model.data.DishesMenuRemoteSource
import com.example.compose.mvvm.ui.NavigationKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DishesCategoryDetailsViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: DishesMenuRemoteSource
) : ViewModel() {

    var state by mutableStateOf(
        DishesCategoryDetailsContract.State(
            null, listOf(
            )
        )
    )
        private set

    init {
        viewModelScope.launch {
            val categoryId = stateHandle.get<String>(NavigationKeys.Arg.DISHES_CATEGORY_ID)
                ?: throw IllegalStateException("No categoryId was passed to destination.")
            val categories = repository.getDishesCategories()
            val category = categories.first { it.id == categoryId }
            state = state.copy(category = category)
            val dishesItems = repository.getMealsByCategory(categoryId)
            state = state.copy(categoryDishesItems = dishesItems)
        }
    }

}
