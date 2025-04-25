package me.diarye.presentation.view.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.diarye.data.local_database.dao.DiaryDao
import me.diarye.presentation.model.DiaryEntry
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val diaryDao: DiaryDao,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val id = savedStateHandle["entryId"] ?: ""
    private val _diary = MutableStateFlow<DiaryEntry?>(null)
    val diary = _diary.onStart {
        viewModelScope.launch(Dispatchers.IO) {
            val diary = diaryDao.getDiaryById(id)
            _diary.update {
                DiaryEntry(
                    id = diary.id,
                    originalText = diary.text,
                    summary = diary.title,
                    timestamp = diary.date
                )
            }
        }
    }.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = null
    )
}