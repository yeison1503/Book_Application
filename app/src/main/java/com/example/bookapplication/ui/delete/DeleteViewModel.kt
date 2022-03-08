package com.example.bookapplication.ui.delete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookapplication.local.Book
import com.example.bookapplication.local.localRepository.BookRepository
import com.example.bookapplication.server.BookServer
import com.example.bookapplication.server.repository.BookServerRepository
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DeleteViewModel : ViewModel() {

    private val bookAplication = BookRepository()
    private val bookServerRepository = BookServerRepository()

    private val findbook: MutableLiveData<Book> = MutableLiveData()
    val findBookDone: LiveData<Book> = findbook

    private val findbookServer: MutableLiveData<BookServer?> = MutableLiveData()
    val findBookServerDone: MutableLiveData<BookServer?> = findbookServer

    fun searchBook(nameBook: String) {
        GlobalScope.launch(Dispatchers.IO) {
            //Para eliminar localmente
            //findbook.postValue(bookAplication.searchBook(nameBook))

            //Para eliminar en el servidor
            val result = bookServerRepository.loadBooksServer()
                var isFoundBook = false
                for (document in result) {
                    val bookServer = document.toObject<BookServer>()
                    if (nameBook == bookServer.name) {
                        findbookServer.postValue(bookServer)
                        isFoundBook = true
                    }
                }
                if(!isFoundBook) findbookServer.postValue(null)
        }
    }

    fun deleteBook(book: Book) {
        GlobalScope.launch(Dispatchers.IO) {
            bookAplication.deleteBook(book)
        }
    }

    fun deleteBookServer(book: BookServer) {
        GlobalScope.launch(Dispatchers.IO) {
            bookServerRepository.deleteBookServer(book)
        }
    }
}