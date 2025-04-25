package me.diarye.presentation.view.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import me.diarye.presentation.view.detail.DiaryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryDetailScreen(
    entryId: String?,
    onBack: () -> Unit,
    viewModel: DiaryViewModel = hiltViewModel(),
) {
    val entry = viewModel.diary.collectAsStateWithLifecycle().value

    Scaffold(topBar = {
        TopAppBar(title = { Text("Entry Detail") }, navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        })
    }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            if (entry != null) {
                Text("Original Text:", style = MaterialTheme.typography.titleMedium)
                Text(entry.originalText, modifier = Modifier.padding(vertical = 8.dp))

                Text("AI Summary:", style = MaterialTheme.typography.titleMedium)
                Text(entry.summary)
            } else {
                Text("Entry not found.")
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    EntryDetailScreen("", {})
}
