package com.example.compose.mvvm.model.response

import com.google.gson.annotations.SerializedName

data class DishesCategoriesResponse(val categories: List<DishesCategoryResponse>)
data class MealsResponse(val meals: List<MealResponse>)


data class DishesCategoryResponse(
    @SerializedName("idCategory") val id: String,
    @SerializedName("strCategory") val name: String,
    @SerializedName("strCategoryThumb") val thumbnailUrl: String,
    @SerializedName("strCategoryDescription") val description: String = ""
)

data class MealResponse(
    @SerializedName("idMeal") val id: String,
    @SerializedName("strMeal") val name: String,
    @SerializedName("strMealThumb") val thumbnailUrl: String,
)
