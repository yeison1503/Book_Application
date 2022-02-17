package com.example.bookapplication.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookapplication.databinding.FragmentListBinding
import com.example.bookapplication.local.Book


class ListFragment : Fragment() {

    private lateinit var listBinding: FragmentListBinding
    private lateinit var listviewModel: ListViewModel
    private lateinit var booksAdapter: BooksAdapter
    private var booksList: ArrayList<Book> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listBinding = FragmentListBinding.inflate(inflater, container, false)
        listviewModel = ViewModelProvider(this)[ListViewModel::class.java]
        return listBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        listviewModel.loadBooksDone.observe(viewLifecycleOwner, {result->
            onLoadBooksDoneSubscribe(result)
        })

        listviewModel.loadBooks()


        booksAdapter = BooksAdapter(booksList)

        listBinding.booksRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListFragment.requireContext())
            adapter = booksAdapter
            setHasFixedSize(false)
        }

        listBinding.newButton.setOnClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToNewBookFragment())
        }

    }

    private fun onLoadBooksDoneSubscribe(booksListLoaded: ArrayList<Book>) {
        booksList = booksListLoaded
        booksAdapter.appendItems(booksList)
    }


}