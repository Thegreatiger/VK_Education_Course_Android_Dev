package vk.education.android.dev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import vk.education.android.dev.data.mapper.AppMapper
import vk.education.android.dev.data.repository.AppRepositoryImpl
import vk.education.android.dev.domain.usecase.GetAppsUseCase
import vk.education.android.dev.presentation.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                RootNavHost()
            }
        }
    }
}

@Composable
fun RootNavHost() {
    val navController = rememberNavController()

    val mapper = AppMapper()
    val repository = AppRepositoryImpl(mapper)
    val useCase = GetAppsUseCase(repository)

    NavHost(
        navController = navController,
        startDestination = AppListDestination
    ) {
        composable<AppListDestination> {
            val viewModel: AppListViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return AppListViewModel(useCase) as T
                    }
                }
            )

            AppListScreen(
                viewModel = viewModel,
                onAppClick = { clickedApp ->
                    navController.navigate(
                        AppDetailsDestination(
                            name = clickedApp.name,
                            description = clickedApp.description
                        )
                    )
                }
            )
        }

        composable<AppDetailsDestination> { backStackEntry ->
            val dest = backStackEntry.toRoute<AppDetailsDestination>()
            AppDetailsScreen(
                appName = dest.name,
                description = dest.description,
                onBackClick = { navController.popBackStack() },
                onShowcaseAppClick = {
                    navController.navigate(AppListDestination) {
                        popUpTo<AppDetailsDestination> { inclusive = true }
                    }
                }
            )
        }
    }
}