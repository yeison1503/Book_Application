package com.example.bookapplication.model

import java.io.Serializable

data class Book(
    val name: String,
    val author: String,
    val pages: Int,
    val abstract: String,
    val genre: String,
    val score: Int,
    val publicationDate: String
): Serializable
