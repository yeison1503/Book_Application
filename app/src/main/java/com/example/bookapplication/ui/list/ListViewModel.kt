package com.example.bookapplication.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookapplication.local.Book
import com.example.bookapplication.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    val bookRepository = BookRepository()

    private val loadBooks : MutableLiveData<ArrayList<Book>> = MutableLiveData()
    val loadBooksDone: LiveData<ArrayList<Book>> = loadBooks

    fun loadBooks() {
        GlobalScope.launch(Dispatchers.IO) {
            loadBooks.postValue(bookRepository.loadBooks())
        }
    }
}