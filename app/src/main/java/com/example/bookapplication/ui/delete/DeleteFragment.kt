package com.example.bookapplication.ui.delete

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.system.Os.accept
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.sqlite.db.SupportSQLiteCompat.Api16Impl.cancel
import com.example.bookapplication.R
import com.example.bookapplication.databinding.FragmentDeleteBinding
import com.example.bookapplication.local.Book
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DeleteFragment : Fragment() {

    private lateinit var deleteBinding: FragmentDeleteBinding
    private lateinit var deleteviewModel: DeleteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        deleteBinding = FragmentDeleteBinding.inflate(inflater, container, false)
        deleteviewModel = ViewModelProvider(this)[DeleteViewModel::class.java]
        return deleteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deleteviewModel.findBookDone.observe(viewLifecycleOwner, {result ->
            onFindBookDoneSuscribe(result)
        })

        with(deleteBinding){
            searchButton.setOnClickListener{
                deleteviewModel.searchBook(nameEditText.text.toString())
            }
        }
    }

    private fun onFindBookDoneSuscribe(book: Book) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.warning_title))
            .setMessage(resources.getString(R.string.delete_book_msg, book.name, book.author))
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                deleteviewModel.deleteBook(book)
                deleteBinding.nameEditText.text?.clear()
            }
            .show()
      }
}