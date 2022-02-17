package com.example.bookapplication

import android.app.Application
import androidx.room.Room
import com.example.bookapplication.local.BookDatabase

class BookAplication: Application() {

    companion object{
        lateinit var database: BookDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            BookDatabase::class.java,
            "book_db"
        ).build()
    }
}