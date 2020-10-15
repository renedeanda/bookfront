package io.rede.bookfront.view.ui.activity

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.browser.customtabs.CustomTabsIntent
import coil.load
import coil.transform.RoundedCornersTransformation
import io.rede.bookfront.Key
import io.rede.bookfront.R
import io.rede.bookfront.model.Book
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.activity_main.toolbar_actionbar
import kotlinx.android.synthetic.main.toolbar_default.*


class BookDetailActivity : AppCompatActivity() {

    var mBook: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        //Set custom toolbar title & enable home back button
        val toolbar = (toolbar_actionbar as Toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar_title.text = getString(R.string.book_details)

        //Load Book object from other activity or savedInstanceState
        if (savedInstanceState != null) {
            mBook = savedInstanceState.getParcelable(Key.LIST_NAME_ENCODED)
        } else {
            mBook = intent.getParcelableExtra(Key.BOOK)
        }

        title_textview.text = mBook?.title
        book_cover_imageview.load(mBook?.bookImage) {
            crossfade(true)
            transformations(RoundedCornersTransformation(5F))
        }
        title_textview.text = mBook?.title
        author_textview.text = mBook?.author
        rank_weeks_textview.text = getString(
            R.string.rank_weeks_on_list,
            mBook?.rank.toString(),
            mBook?.weeksOnList.toString()
        )
        description_textview.text = mBook?.description
        publisher_textview.text = getString(R.string.publisher_x, mBook?.publisher)
        isbn10_textview.text = getString(R.string.isbn10_x, mBook?.primaryIsbn10)
        isbn13_textview.text = getString(R.string.isbn13_x, mBook?.primaryIsbn13)

        if (mBook?.amazonProductUrl != "") {
            amazon_button.visibility = View.VISIBLE
            amazon_button.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(this, Uri.parse(mBook?.amazonProductUrl))
            }
        }

    }
    //Save Book object
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(Key.BOOK, mBook)
    }

    //Allow home back presses
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