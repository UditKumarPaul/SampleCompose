package com.example.compose.mvvm.model

data class DishesItem(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val description: String = ""
)
