package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.screen.*

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "first"
    ) {

        // -------- FIRST --------
        composable("first") {
            FirstScreen(
                name = "User",
                navController = navController
            )
        }

        // -------- SECOND --------
        composable("second") {
            SecondScreen(navController = navController)
        }

        // -------- THIRD --------
        composable(
            route = "third?from={from}&to={to}",
            arguments = listOf(
                navArgument("from") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("to") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->

            val from = backStackEntry.arguments?.getString("from") ?: ""
            val to = backStackEntry.arguments?.getString("to") ?: ""

            ThirdScreen(
                navController = navController,
                from = from,
                to = to
            )
        }
        // 🔹 FOURTH SCREEN
        composable(
            route = "fourth/{from}/{to}/{date}/{time}/{passengers}"
        ) { backStackEntry ->

            FourthScreen(
                navController = navController,
                from = backStackEntry.arguments?.getString("from") ?: "",
                to = backStackEntry.arguments?.getString("to") ?: "",
                date = backStackEntry.arguments?.getString("date") ?: "",
                time = backStackEntry.arguments?.getString("time") ?: "",
                passengers = backStackEntry.arguments?.getString("passengers")?.toInt() ?: 1
            )
        }




    }
}
