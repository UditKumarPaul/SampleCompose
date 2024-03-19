package com.example.compose.mvvm.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose.mvvm.theme.ComposeSampleTheme
import com.example.compose.mvvm.ui.NavigationKeys.Arg.DISHES_CATEGORY_ID
import com.example.compose.mvvm.ui.feature.categories.DishesCategoriesScreen
import com.example.compose.mvvm.ui.feature.categories.DishesCategoriesViewModel
import com.example.compose.mvvm.ui.feature.category_details.DishesCategoryDetailsScreen
import com.example.compose.mvvm.ui.feature.category_details.DishesCategoryDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.receiveAsFlow


// Single Activity per app
@AndroidEntryPoint
class LauncherActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                DishesApp()
            }
        }
    }

}

@Composable
private fun DishesApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationKeys.Route.DISHES_CATEGORIES_LIST) {
        composable(route = NavigationKeys.Route.DISHES_CATEGORIES_LIST) {
            DishesCategoriesDestination(navController)
        }
        composable(
            route = NavigationKeys.Route.DISHES_CATEGORY_DETAILS,
            arguments = listOf(navArgument(DISHES_CATEGORY_ID) {
                type = NavType.StringType
            })
        ) {
            DishesCategoryDetailsDestination()
        }
    }
}

@Composable
private fun DishesCategoriesDestination(navController: NavHostController) {
    val viewModel: DishesCategoriesViewModel = hiltViewModel()
    DishesCategoriesScreen(
        state = viewModel.state,
        effectFlow = viewModel.effects.receiveAsFlow(),
        onNavigationRequested = { itemId ->
            navController.navigate("${NavigationKeys.Route.DISHES_CATEGORIES_LIST}/${itemId}")
        })
}

@Composable
private fun DishesCategoryDetailsDestination() {
    val viewModel: DishesCategoryDetailsViewModel = hiltViewModel()
    DishesCategoryDetailsScreen(viewModel.state)
}

object NavigationKeys {

    object Arg {
        const val DISHES_CATEGORY_ID = "dishesCategoryName"
    }

    object Route {
        const val DISHES_CATEGORIES_LIST = "dishes_categories_list"
        const val DISHES_CATEGORY_DETAILS = "$DISHES_CATEGORIES_LIST/{$DISHES_CATEGORY_ID}"
    }

}