package com.samsung.android.notepad

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samsung.android.notepad.data.NoteEntity

@BindingAdapter("app:items")
fun setList(recyclerView: RecyclerView, items: List<NoteEntity>?) {
    items?.let {
        (recyclerView.adapter as NoteListAdapter).setData(it)
    }
}