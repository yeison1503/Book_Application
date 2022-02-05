package com.example.bookapplication.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookapplication.R
import com.example.bookapplication.databinding.ActivityRegisterBinding
import com.example.bookapplication.databinding.FragmentListBinding
import com.example.bookapplication.model.Book

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


        booksList.add(
            Book(
                "EL SICOANALISTA",
                "JOHN KASTZENBACH",
                200,
                "MUY BUENO",
                "Suspenso",
                5,
                "10-ene-2000"
            )
        )

        booksList.add(
            Book(
                "La chica del Tren",
                "Paula Hawkins",
                200,
                "Una china con opceción",
                "Suspenso",
                4,
                "1-feb-2018"
            )
        )
        booksList.add(
            Book(
                "El Principito",
                "Antoine Exupery",
                150,
                "Erase una vez",
                "",
                5,
                "10-mar-1991"
            )
        )

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


}