package com.prtech.wishlistapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.prtech.wishlistapp.ui.theme.Screen
import com.prtech.wishlistapp.ui.theme.WishViewModel
import java.util.Map.entry


@Composable
fun Navigation(viewModel: WishViewModel = viewModel(), navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ){
        composable(Screen.HomeScreen.route){
            HomeView(navController, viewModel)
        }
        composable(Screen.AddScreen.route + "/{id}" ,
            arguments = listOf(
                navArgument("id"){
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )){entry ->
            val id = entry.arguments?.getLong("id") ?: 0L

            AddEditDetailView(id = id, viewModel = viewModel, navController = navController)
        }
    }
}

