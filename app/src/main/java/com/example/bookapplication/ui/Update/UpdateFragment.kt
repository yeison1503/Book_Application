package com.example.bookapplication.ui.Update

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bookapplication.R
import com.example.bookapplication.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private lateinit var updatedBinding: FragmentUpdateBinding
    private lateinit var updatedViewModel: UpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        updatedBinding = FragmentUpdateBinding.inflate(inflater, container, false)
        updatedViewModel = ViewModelProvider(this)[UpdateViewModel::class.java]
        return updatedBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}