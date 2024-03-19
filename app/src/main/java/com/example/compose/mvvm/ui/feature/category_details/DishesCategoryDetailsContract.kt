package com.example.compose.mvvm.ui.feature.category_details


import com.example.compose.mvvm.model.DishesItem


class DishesCategoryDetailsContract {
    data class State(
        val category: DishesItem?,
        val categoryDishesItems: List<DishesItem>
    )
}