package com.samsung.android.notepad.data

import com.samsung.android.notepad.NEW_NOTE_ID
import java.util.*

data class NoteEntity (
    var id: Int,
    var data: Date,
    var text: String
) {
    constructor() : this(NEW_NOTE_ID, Date(), "")
    constructor(date: Date, text: String) : this(NEW_NOTE_ID, date, text)
}