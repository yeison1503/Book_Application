package com.example.bookapplication.ui.delete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookapplication.local.Book
import com.example.bookapplication.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DeleteViewModel : ViewModel() {

    val bookAplication = BookRepository()

    private val findbook:MutableLiveData<Book> = MutableLiveData()
    val findBookDone: LiveData<Book> = findbook

    fun searchBook(nameBook: String) {
        GlobalScope.launch(Dispatchers.IO) {
            findbook.postValue(bookAplication.searchBook(nameBook))
        }
    }

    fun deleteBook(book: Book) {
        GlobalScope.launch(Dispatchers.IO) {
            bookAplication.deleteBook(book)
        }
    }
}