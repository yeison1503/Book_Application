package com.example.bookapplication.ui.Update

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.bookapplication.databinding.FragmentUpdateBinding
import com.example.bookapplication.local.Book

class UpdateFragment : Fragment() {

    private lateinit var updatedBinding: FragmentUpdateBinding
    private lateinit var updatedViewModel: UpdateViewModel
    private lateinit var book: Book

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

        updatedViewModel.findBookDone.observe(viewLifecycleOwner, {result ->
            onFindBookDoneSubscribe(result)
        })

        with(updatedBinding){
            searchButton.setOnClickListener {
                updatedViewModel.searchBook(nameEditText.text.toString())
            }

            updateButton.setOnClickListener{
                book.name = nameEditText.text.toString()
                book.author = nameAuthorEditText.text.toString()
                book.pages = pagesEditText.text.toString().toInt()
                book.resumen = abstractEditText.text.toString()

                updatedViewModel.updateBook(book)
            }
        }
    }

    private fun onFindBookDoneSubscribe(book: Book) {
        this.book = book
        with(updatedBinding){
            updateFormLayout.isVisible = true
            searchButton.isVisible = false
            updateButton.isVisible = true
            nameAuthorEditText.setText(book.author)
            pagesEditText.setText(book.pages.toString())
            abstractEditText.setText(book.resumen)
            publicationDateButton.text = book.publicationDate
            when (book.score) {
                1 -> oneRadioButton.isChecked = true
                2 -> twoRadioButton.isChecked = true
                3 -> threeRadioButton.isChecked = true
                4 -> fourRadioButton.isChecked = true
                else -> fiveRadioButton.isChecked = true
            }
        }
    }

}