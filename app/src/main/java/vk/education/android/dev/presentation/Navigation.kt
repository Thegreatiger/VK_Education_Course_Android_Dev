package vk.education.android.dev.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            AppListScreen(onAppClick = { id ->
                navController.navigate("details/$id")
            })
        }
        composable("details/{appId}") {
            AppDetailsScreen(onBackClick = { navController.popBackStack() })
        }
    }
}