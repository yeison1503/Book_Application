package com.example.bookapplication.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookapplication.local.Book
import com.example.bookapplication.local.localRepository.BookRepository
import com.example.bookapplication.server.BookServer
import com.example.bookapplication.server.repository.BookServerRepository
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    private val bookRepository = BookRepository()
    private val bookServerRepository = BookServerRepository()
    private var booksList: ArrayList<BookServer> = ArrayList()

    private val loadBooks : MutableLiveData<ArrayList<Book>> = MutableLiveData()
    val loadBooksDone: LiveData<ArrayList<Book>> = loadBooks

    private val loadBooksFromServer : MutableLiveData<ArrayList<BookServer>> = MutableLiveData()
    val loadBooksServerDone: LiveData<ArrayList<BookServer>> = loadBooksFromServer

    fun loadBooks() {
        GlobalScope.launch(Dispatchers.IO) {
            loadBooks.postValue(bookRepository.loadBooks())
        }
    }

    fun loadBooksServer() {
        GlobalScope.launch(Dispatchers.IO) {

            val QuerySnapshot = bookServerRepository.loadBooksServer()
            for (document in QuerySnapshot) {
                val bookServer = document.toObject<BookServer>()
                booksList.add(bookServer)
            }
            loadBooksFromServer.postValue( booksList)
        }
    }
}