package me.diarye.presentation.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.diarye.data.local_database.dao.DiaryDao
import me.diarye.presentation.model.DiaryEntry
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val diaryDao: DiaryDao) : ViewModel() {
    private val _entries = MutableStateFlow(emptyList<DiaryEntry>())
    val entries = _entries
        .onStart {
            viewModelScope.launch(Dispatchers.IO) {
                _entries.update {
                    diaryDao.getDiary().map {
                        DiaryEntry(
                            id = it.id,
                            originalText = it.text,
                            summary = it.title,
                            timestamp = it.date
                        )
                    }
                }
            }

        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )
}