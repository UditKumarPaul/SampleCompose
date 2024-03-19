package com.example.compose.mvvm.model.data

import com.example.compose.mvvm.model.response.DishesCategoriesResponse
import com.example.compose.mvvm.model.response.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DishesMenuApi @Inject constructor(private val service: Service) {

    suspend fun getDishesCategories(): DishesCategoriesResponse = service.getDishesCategories()
    suspend fun getMealsByCategory(categoryId: String): MealsResponse =
        service.getMealsByCategory(categoryId)

    interface Service {
        @GET("categories.php")
        suspend fun getDishesCategories(): DishesCategoriesResponse

        @GET("filter.php")
        suspend fun getMealsByCategory(@Query("c") categoryId: String): MealsResponse
    }

    companion object {
        const val API_URL = "https://www.themealdb.com/api/json/v1/1/"
    }
}


