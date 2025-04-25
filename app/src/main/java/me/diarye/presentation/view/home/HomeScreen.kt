package me.diarye.presentation.view.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import me.diarye.presentation.view.detail.DiaryViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(
    onAddNew: () -> Unit,
    onEntryClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val entries = viewModel.entries.collectAsStateWithLifecycle().value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNew) {
                Icon(Icons.Default.Add, contentDescription = "New Entry")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(entries.size) { i ->
                ListItem(
                    headlineContent = { Text(entries[i].summary) },
                    supportingContent = {
                        Text(
                            SimpleDateFormat(
                                "MMM dd, yyyy HH:mm",
                                Locale.getDefault()
                            ).format(Date(entries[i].timestamp.toEpochMilli()))
                        )
                    },
                    modifier = Modifier.clickable { onEntryClick(entries[i].id) },
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    HomeScreen({}, {})
}