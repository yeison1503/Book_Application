package com.example.bookapplication.local

import androidx.room.*

@Dao
interface BookDao {

    @Insert
    suspend fun saveBook(book: Book)

    @Query("SELECT * FROM table_books WHERE name LIKE :nameBook")
    suspend fun searchBook(nameBook: String): Book

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("SELECT * FROM table_books")
    suspend fun loadBooks(): MutableList<Book>

    @Update
    suspend fun updateBook(book: Book)
}