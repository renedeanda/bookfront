package io.rede.bookfront.view.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import io.rede.bookfront.Key
import io.rede.bookfront.R
import io.rede.bookfront.model.Book
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar_actionbar

class BookDetailActivity : AppCompatActivity() {

    var mBook: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val toolbar = (toolbar_actionbar as Toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState != null) {
            mBook = savedInstanceState.getParcelable(Key.LIST_NAME_ENCODED)
        } else {
            mBook = intent.getParcelableExtra(Key.BOOK)
        }

        title_textview.text = mBook?.title
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(Key.BOOK, mBook)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}