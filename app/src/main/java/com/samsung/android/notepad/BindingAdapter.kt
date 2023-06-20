package com.samsung.android.notepad

import android.graphics.drawable.AdaptiveIconDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samsung.android.notepad.data.NoteEntity

@BindingAdapter("app:items")
fun setList(recyclerView: RecyclerView, items: List<NoteEntity>?) {
    items?.let {
        (recyclerView.adapter as NoteListAdapter).setData(it)
    }
}

@BindingAdapter("app:srcCompat")
fun setImage(imageView: ImageView, drawable: Drawable) {
    imageView.setImageDrawable(drawable)
}