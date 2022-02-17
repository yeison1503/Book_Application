package com.example.bookapplication.repository

import com.example.bookapplication.BookAplication
import com.example.bookapplication.local.Book
import com.example.bookapplication.local.BookDao
import java.sql.Types.NULL

class BookRepository {
    suspend fun saveBook(
        nameBook: String,
        author: String,
        pages: Int,
        resumen: String,
        genre: String,
        score: Int,
        publicationDate: String
    ) {
        val book = Book(
            id = NULL,
            name = nameBook,
            author = author,
            pages = pages,
            resumen = resumen,
            genre = genre,
            score = score,
            publicationDate = publicationDate
        )
        val bookDao: BookDao = BookAplication.database.BookDao()
        bookDao.saveBook(book)

    }

    suspend fun searchBook(nameBook: String): Book {
        val bookDao: BookDao = BookAplication.database.BookDao()
        val book = bookDao.searchBook(nameBook)
        return book

    }

    suspend fun deleteBook(book: Book) {
        val bookDao: BookDao = BookAplication.database.BookDao()
        bookDao.deleteBook(book)
    }

    suspend fun loadBooks(): ArrayList<Book> {
        val bookDao: BookDao = BookAplication.database.BookDao()
        val booksList: ArrayList<Book> = bookDao.loadBooks() as ArrayList<Book>
        return booksList
    }

    suspend fun updateBook(book: Book) {
        val bookDao: BookDao = BookAplication.database.BookDao()
        bookDao.updateBook(book)
    }
}