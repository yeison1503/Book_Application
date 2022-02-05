package com.example.bookapplication.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookapplication.R
import com.example.bookapplication.databinding.CardViewItemBookBinding
import com.example.bookapplication.model.Book

class BooksAdapter (
    private val bookslist: ArrayList<Book>
    ): RecyclerView.Adapter<BooksAdapter.BookViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookslist[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int = bookslist.size

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = CardViewItemBookBinding.bind(itemView)
        fun bind(book: Book) {
            with(binding){
                nameBookTextView.text = book.name
                authorTextView.text = book.author
            }
        }
    }
}