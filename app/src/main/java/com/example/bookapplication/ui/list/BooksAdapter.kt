package com.example.bookapplication.ui.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookapplication.R
import com.example.bookapplication.databinding.CardViewItemBookBinding
import com.example.bookapplication.server.BookServer

class BooksAdapter (
    private val bookslist: ArrayList<BookServer>,
    private val onItemClicked: (BookServer) -> Unit
    ): RecyclerView.Adapter<BooksAdapter.BookViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookslist[position]
        holder.bind(book)
        holder.itemView.setOnClickListener{ onItemClicked(bookslist[position])}
    }

    override fun getItemCount(): Int = bookslist.size

    fun appendItems(newList: ArrayList<BookServer>) {
        bookslist.clear()
        bookslist.addAll(newList)
        notifyDataSetChanged()
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = CardViewItemBookBinding.bind(itemView)
        private val context = binding.root

        fun bind(book: BookServer) {
            with(binding){
                nameBookTextView.text = book.name
                authorTextView.text = book.author
                Glide.with(context).load(book.urlPicture).into(pictureBookImageView)
            }
            itemView.setOnClickListener{
                Log.d("click", "en item")
            }
        }
    }
}