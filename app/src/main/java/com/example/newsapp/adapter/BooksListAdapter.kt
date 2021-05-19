package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.model.Book
import com.squareup.picasso.Picasso

class BooksListAdapter(var dataSource: List<Book>) :
    RecyclerView.Adapter<BooksListAdapter.ViewHolder>() {

    var onImageClick: ((String?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.books_list_item, parent, false)
        val holder = ViewHolder(view)

        holder.container.setOnClickListener {
            onImageClick?.invoke(dataSource[holder.bindingAdapterPosition].volumeInfo?.infoLink)
        }
        return holder
    }

    override fun getItemCount() = dataSource.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataSource[position].volumeInfo?.title

        if (!dataSource[position].volumeInfo?.imageLinks?.thumbnail.isNullOrEmpty()) {
            Picasso.get().load(dataSource[position].volumeInfo?.imageLinks?.thumbnail?.replace("http:", "https:"))
                .placeholder(R.drawable.picture_placeholder)
                .error(R.drawable.picture_placeholder).into(holder.imageView)
        } else {
            Picasso.get().load(R.drawable.picture_placeholder).error(R.drawable.picture_placeholder)
                .into(holder.imageView)
        }
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val container: CardView = itemView.findViewById(R.id.container)
    }
}