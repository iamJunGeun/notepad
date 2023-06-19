package com.samsung.android.notepad

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.samsung.android.notepad.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NoteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        with(binding.recyclerView) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation)
            addItemDecoration(divider)
        }

        adapter = NoteListAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.addSampleNotes()

        Log.i(TAG, "onCreateView")

        return binding.root
    }
}