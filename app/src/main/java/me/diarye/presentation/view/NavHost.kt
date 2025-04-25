package me.diarye.presentation.view

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object NewEntry : Screen("new_entry")
    object EntryDetail : Screen("entry_detail/{entryId}") {
        fun createRoute(entryId: String) = "entry_detail/$entryId"
    }
}

