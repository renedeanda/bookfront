package io.rede.bookfront.view.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.rede.bookfront.R
import io.rede.bookfront.model.Book
import io.rede.bookfront.view.ui.activity.BookDetailActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.book_item.*

class BookAdapter(private val books: List<Book>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        LayoutContainer, View.OnClickListener {
        override val containerView: View?
            get() = itemView

        fun bind() {
            val title = books[adapterPosition].title
            title_textview.text = title
        }

        override fun onClick(view: View) {
            val detailIntent = Intent(itemView.context, BookDetailActivity::class.java)
            detailIntent.putExtra("book", books[adapterPosition])
            itemView.context.startActivity(detailIntent)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return books.size
    }
}