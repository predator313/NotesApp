package com.example.notesapp.feature_note.presentation.notes

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.core.utils.ORDER_SECTION
import com.example.notesapp.di.AppModule
import com.example.notesapp.feature_note.presentation.MainActivity
import com.example.notesapp.feature_note.presentation.utils.Screen
import com.example.notesapp.ui.theme.NotesAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NoteScreenKtTest{
    @get:Rule(order = 0)  //order specify the priority in which the rule will execute,zero first priority
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order=1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp(){
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            NotesAppTheme {
                NavHost(navController = navController,
                    startDestination = Screen.NoteScreen.route
                    ){
                    composable(route = Screen.NoteScreen.route){
                        NoteScreen(navController = navController)
                    }
                }
            }
        }
    }
    @Test
    fun clickToggleOrderSection_isVisible(){
        composeRule.onNodeWithTag(ORDER_SECTION).assertDoesNotExist() //try to find but make sure it
        //doesn't exit
        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithTag(ORDER_SECTION).assertIsDisplayed()
    }
}