package com.example.bookapplication.server

import java.io.Serializable

data class BookServer(
    var id: String? = null,
    var name: String? = null,
    var author: String? = null,
    var pages: Int? = null,
    var resumen: String? = null,
    var genre: String? = null,
    var score: Int? = null,
    var publicationDate: String? = null,
    var urlPicture: String? = null
): Serializable