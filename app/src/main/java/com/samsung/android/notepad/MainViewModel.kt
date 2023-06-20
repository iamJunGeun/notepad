package com.samsung.android.notepad

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.*
import com.samsung.android.notepad.data.AppDatabase
import com.samsung.android.notepad.data.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val database = AppDatabase.getInstance(app)
    //private val _noteList = MutableLiveData<ArrayList<NoteEntity>>()
    private val _noteList = database.noteDao()?.getAll()
    val noteList : LiveData<List<NoteEntity>>?
        get() = _noteList
    private val selectedList = arrayListOf<NoteEntity>()

    private val timer = object : CountDownTimer(10000, 2000) {
        var id = 1
        override fun onTick(p0: Long) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    val str = "%d test string".format(id)
                    database.noteDao()?.insertNote(NoteEntity(id, Date(), str))
                    ++id
                }
            }
        }

        override fun onFinish() {
        }
    }

    fun addSampleNotes() {
        _noteList?.value?.let {
            timer.id = if (it.isEmpty()) 1 else it.last().id + 1
        }
        timer.start()
    }

    fun deleteAllNotes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.noteDao()?.deleteAll()
            }
        }
    }

    fun selectNote(note: NoteEntity) {
        if (selectedList.contains(note)) {
            selectedList.remove(note)
            note.selected = false
        } else {
            selectedList.add(note)
            note.selected = true
        }
    }

    fun hasSelectedNotes(): Boolean {
        return !selectedList.isEmpty()
    }

    fun deleteSelectedNotes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.noteDao()?.deleteNotes(selectedList)
                selectedList.clear()
            }
        }
    }
}