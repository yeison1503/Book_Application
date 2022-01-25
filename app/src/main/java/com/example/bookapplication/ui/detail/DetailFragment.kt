package com.example.bookapplication.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.bookapplication.R
import com.example.bookapplication.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {


    private lateinit var detailBinding: FragmentDetailBinding
    private lateinit var detailviewModel: DetailViewModel
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        detailviewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val book = args.book

        with(detailBinding){
            nameBookTextView.text = book.name
            authorTextView.text = book.author

        }

    }
}