package com.example.bookapplication.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookapplication.databinding.FragmentListBinding
import com.example.bookapplication.local.Book
import com.example.bookapplication.server.BookServer

class ListFragment : Fragment() {

    private lateinit var listBinding: FragmentListBinding
    private lateinit var listviewModel: ListViewModel
    private lateinit var booksAdapter: BooksAdapter
    private var booksList: ArrayList<Book> = ArrayList()  //Room
    private var booksListFromServer: ArrayList<BookServer> = ArrayList()  //Firebase

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


        listviewModel.loadBooksDone.observe(viewLifecycleOwner) { result ->
            onLoadBooksDoneSubscribe(result)
        }

        listviewModel.loadBooksServerDone.observe(viewLifecycleOwner){ result ->
            onLoadBooksFromServerDoneSubscribe(result)
        }

        //listviewModel.loadBooks()
        listviewModel.loadBooksServer()

        booksAdapter = BooksAdapter(booksListFromServer, onItemClicked = {onBookItemClicked(it)})

        listBinding.booksRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListFragment.requireContext())
            adapter = booksAdapter
            setHasFixedSize(false)
        }

        listBinding.newButton.setOnClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToNewBookFragment())
        }
    }

    private fun onBookItemClicked(book: BookServer) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(book))
        book.name?.let{Log.d("nombre:",it)}
    }

    private fun onLoadBooksFromServerDoneSubscribe(booksListFromServerLoaded: ArrayList<BookServer>) {
        booksListFromServer = booksListFromServerLoaded
        booksAdapter.appendItems(booksListFromServer)
    }        //Firevase

   private fun onLoadBooksDoneSubscribe(booksListLoaded: ArrayList<Book>) {
        //booksList = booksListLoaded
        //booksAdapter.appendItems(booksList)
   }         //Room


}