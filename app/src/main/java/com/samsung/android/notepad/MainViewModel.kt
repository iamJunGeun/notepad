package com.samsung.android.notepad

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samsung.android.notepad.data.NoteEntity
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel : ViewModel() {
    private val _noteList = MutableLiveData<ArrayList<NoteEntity>>()
    val noteList : LiveData<ArrayList<NoteEntity>>
        get() = _noteList

    private var items = ArrayList<NoteEntity>()

    private val timer = object : CountDownTimer(10000, 2000) {
        var id = 1
        override fun onTick(p0: Long) {
            val str = "$id test string"
            items.add(NoteEntity(id, Date(), str))
            _noteList.value = items
            ++id
        }

        override fun onFinish() {
        }
    }

    fun addSampleNotes() {
        timer.id = items.size + 1
        timer.start()
    }
}