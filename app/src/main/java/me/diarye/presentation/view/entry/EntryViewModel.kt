package me.diarye.presentation.view.entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.diarye.data.local_database.dao.DiaryDao
import me.diarye.data.local_database.model.Diary
import me.diarye.presentation.model.DiaryEntry
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(private val diaryDao: DiaryDao) : ViewModel() {
    fun addNew(diaryEntry: DiaryEntry) {
        val diary = Diary(title = diaryEntry.summary, text = diaryEntry.originalText)
        viewModelScope.launch(Dispatchers.IO) {
            diaryDao.addDiary(diary)
        }
    }
}