package com.example.compose.mvvm.model.data

import com.example.compose.mvvm.model.DishesItem
import com.example.compose.mvvm.model.response.DishesCategoriesResponse
import com.example.compose.mvvm.model.response.MealsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DishesMenuRemoteSource @Inject constructor(private val dishesMenuApi: DishesMenuApi) {

    private var cachedCategories: List<DishesItem>? = null

    suspend fun getDishesCategories(): List<DishesItem> = withContext(Dispatchers.IO) {
        var cachedCategories = cachedCategories
        if (cachedCategories == null) {
            cachedCategories = dishesMenuApi.getDishesCategories().mapCategoriesToItems()
            this@DishesMenuRemoteSource.cachedCategories = cachedCategories
        }
        return@withContext cachedCategories
    }

    suspend fun getMealsByCategory(categoryId: String) = withContext(Dispatchers.IO) {
        val categoryName = getDishesCategories().first { it.id == categoryId }.name
        return@withContext dishesMenuApi.getMealsByCategory(categoryName).mapMealsToItems()
    }

    private fun DishesCategoriesResponse.mapCategoriesToItems(): List<DishesItem> {
        return this.categories.asReversed().map { category ->
            DishesItem(
                id = category.id,
                name = category.name,
                description = category.description,
                thumbnailUrl = category.thumbnailUrl
            )
        }
    }

    private fun MealsResponse.mapMealsToItems(): List<DishesItem> {
        return this.meals.asReversed().map { category ->
            DishesItem(
                id = category.id,
                name = category.name,
                thumbnailUrl = category.thumbnailUrl
            )
        }
    }

}