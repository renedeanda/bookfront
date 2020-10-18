package io.rede.bookfront.view.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import io.rede.bookfront.R
import io.rede.bookfront.model.Book
import io.rede.bookfront.view.ui.activity.BookDetailActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.book_item.*

//Adapter for loading Book items in recyclerview
class BookAdapter(private val books: List<Book?>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        LayoutContainer, View.OnClickListener {
        override val containerView: View?
            get() = itemView

        fun bind() {
            book_cover_imageview.load(books[adapterPosition]?.bookImage) {
                crossfade(true)
                transformations(RoundedCornersTransformation(5F))
            }
            title_textview.text = books[adapterPosition]?.title
            author_textview.text = books[adapterPosition]?.author
            rank_weeks_textview.text = itemView.context.getString(
                R.string.rank_weeks_on_list,
                books[adapterPosition]?.rank.toString(),
                books[adapterPosition]?.weeksOnList.toString()
            )
            description_textview.text = books[adapterPosition]?.description
        }

        override fun onClick(view: View) {
            val intent = BookDetailActivity.createIntent(
                itemView.context,
                books[adapterPosition]
            )
            itemView.context.startActivity(intent)
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