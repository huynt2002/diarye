package me.diarye.presentation.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import me.diarye.presentation.view.detail.EntryDetailScreen
import me.diarye.presentation.view.entry.NewEntryScreen
import me.diarye.presentation.view.home.HomeScreen

@Composable
fun VoiceDiaryApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                onAddNew = { navController.navigate(Screen.NewEntry.route) },
                onEntryClick = { entryId ->
                    navController.navigate(Screen.EntryDetail.createRoute(entryId))
                })
        }
        composable(Screen.NewEntry.route) {
            NewEntryScreen(onBack = { navController.popBackStack() })
        }
        composable(
            Screen.EntryDetail.route,
            arguments = listOf(navArgument("entryId") { type = NavType.StringType })
        ) { backStackEntry ->
            val entryId = backStackEntry.arguments?.getString("entryId")
            EntryDetailScreen(
                entryId = entryId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
