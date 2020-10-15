package io.rede.bookfront.view.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.rede.bookfront.R
import io.rede.bookfront.model.BookList
import io.rede.bookfront.view.ui.activity.BookListActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.booklist_item.*

//Adapter for loading Booklist items in recyclerview
class BookListAdapter(private val mBookLists: List<BookList>) :
    RecyclerView.Adapter<BookListAdapter.BookListViewHolder>() {

    inner class BookListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        LayoutContainer, View.OnClickListener {
        override val containerView: View?
            get() = itemView

        fun bind() {
            val displayName = mBookLists[adapterPosition].displayName
            display_name_textview.text = displayName
        }

        override fun onClick(view: View) {
            val intent = BookListActivity.createIntent(
                itemView.context,
                mBookLists[adapterPosition].listNameEncoded
            )
            itemView.context.startActivity(intent)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.booklist_item, parent, false)
        return BookListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return mBookLists.size
    }
}