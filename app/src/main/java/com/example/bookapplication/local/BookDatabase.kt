package com.example.bookapplication.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class BookDatabase: RoomDatabase() {

    abstract fun BookDao(): BookDao

}