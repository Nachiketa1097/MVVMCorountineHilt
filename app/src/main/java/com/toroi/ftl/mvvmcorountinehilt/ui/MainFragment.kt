package com.toroi.ftl.mvvmcorountinehilt.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toroi.ftl.mvvmcorountinehilt.R
import com.toroi.ftl.mvvmcorountinehilt.api.NotesApi
import com.toroi.ftl.mvvmcorountinehilt.util.Constants.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var notesApi: NotesApi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        CoroutineScope(Dispatchers.IO).launch {
            val response = notesApi.getNotes()
            Log.d(TAG, response.body().toString())
        }
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}