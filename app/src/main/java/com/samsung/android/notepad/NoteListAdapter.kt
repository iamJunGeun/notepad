package com.samsung.android.notepad

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samsung.android.notepad.data.NoteEntity
import com.samsung.android.notepad.databinding.ListItemBinding

class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {
    private var noteList = emptyList<NoteEntity> ()

    fun setData(items: List<NoteEntity>) {
        noteList = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(item: NoteEntity) {
                binding.noteEntity = item
                binding.executePendingBindings()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = noteList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(noteList[position])
    }
}