package com.example.compose.mvvm.ui.feature.categories

import com.example.compose.mvvm.model.DishesItem


class DishesCategoriesContract {

    data class State(
        val categories: List<DishesItem> = listOf(),
        val isLoading: Boolean = false
    )

    sealed class Effect {
        object DataWasLoaded : Effect()
    }
}