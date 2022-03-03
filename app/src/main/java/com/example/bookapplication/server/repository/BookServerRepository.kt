package com.example.bookapplication.server.repository

import com.example.bookapplication.server.BookServer
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class BookServerRepository {

    var db = Firebase.firestore

    fun saveBook(nameBook: String, author: String, pages: Int, resumen: String, genre: String, score: Int, publicationDate: String) {

        val documentBook = db.collection("books").document()

        val book = BookServer(
            id = documentBook.id,
            name = nameBook,
            author = author,
            pages = pages,
            resumen = resumen,
            genre = genre,
            score = score,
            publicationDate = publicationDate
        )

        db.collection("books").document(documentBook.id).set(book)
    }

    fun searchBook(nameBook: String): BookServer? {

        var bookServerFound: BookServer? = null

        db.collection("books").get()
            .addOnSuccessListener { result ->
            for (document in result) {
                val bookServer = document.toObject<BookServer>()
                if (nameBook == bookServer.name) {
                    bookServerFound = bookServer
                }
            }
        }
        return bookServerFound
    }

    fun deleteBookServer(book: BookServer) {
        book.id?.let { id ->
            db.collection("books").document(id).delete() }
    }
}