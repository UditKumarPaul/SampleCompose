package com.example.compose.mvvm.ui.feature.categories

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DishesCategoriesScreenKtTest {

    @get: Rule
    val composeTestRule = createComposeRule()
    private val item1 = hasText("Vegan") and hasClickAction()

    @org.junit.jupiter.api.BeforeEach
    fun setUp() {
        composeTestRule.waitForIdle()
    }

    @org.junit.jupiter.api.AfterEach
    fun tearDown() {
    }

    @org.junit.jupiter.api.Test
    fun dishesCategoriesList() {
        composeTestRule.onNodeWithTag("Dishes Categories List").assertIsDisplayed()
    }

    @org.junit.jupiter.api.Test
    fun dishesItemRow() {
        composeTestRule.onNodeWithTag("Dishes Item Row").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Dishes Item Row").assertHasClickAction()
        composeTestRule.onNodeWithTag("Item Column").performScrollToNode(item1).performClick()
    }

    @org.junit.jupiter.api.Test
    fun dishesItemDetails() {
        composeTestRule.onNodeWithTag("Dishes Item Details").assertIsDisplayed()
    }

    @org.junit.jupiter.api.Test
    fun dishesItemThumbnail() {
        composeTestRule.onNodeWithTag("Dishes Item Thumbnail").assertIsDisplayed()
    }

}