package io.rede.bookfront.view.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import io.rede.bookfront.Key
import io.rede.bookfront.R
import io.rede.bookfront.model.Book
import io.rede.bookfront.model.BookListResponse
import io.rede.bookfront.network.GetBookData
import io.rede.bookfront.network.RetrofitInstance
import io.rede.bookfront.view.ui.adapter.BookAdapter
import kotlinx.android.synthetic.main.activity_book_list.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.swipe_refresh_layout
import kotlinx.android.synthetic.main.activity_main.toolbar_actionbar
import kotlinx.android.synthetic.main.toolbar_default.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class BookListActivity : AppCompatActivity() {

    var mListNameEncoded: String? = null
    var mBooks: List<Book> = ArrayList()
    var mBookAdapter: BookAdapter? = null
    var mService = RetrofitInstance.retrofitInstance!!.create<GetBookData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        val toolbar = (toolbar_actionbar as Toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState != null) {
            mListNameEncoded = savedInstanceState.getString(Key.LIST_NAME_ENCODED)
        } else {
            mListNameEncoded = intent.getStringExtra(Key.LIST_NAME_ENCODED)
        }

        if (mListNameEncoded != null) {
            loadBooks(mListNameEncoded)
        } else {
            Toast.makeText(this@BookListActivity, "No List Name Selected", Toast.LENGTH_SHORT)
                .show()
        }

        swipe_refresh_layout.setColorSchemeColors(
            ContextCompat.getColor(
                this@BookListActivity,
                R.color.purple_500
            )
        )
        swipe_refresh_layout.setOnRefreshListener { loadBooks(mListNameEncoded) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(Key.LIST_NAME_ENCODED, mListNameEncoded)
    }

    private fun loadBooks(listNameEncoded: String?) {
        swipe_refresh_layout.isRefreshing = true
        val call: Call<BookListResponse> = mService.getTopBooks(listNameEncoded)
        call.enqueue(object : Callback<BookListResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<BookListResponse>,
                response: Response<BookListResponse>
            ) {
                swipe_refresh_layout.isRefreshing = false
                if (response.body() != null && response.body()!!.results != null) {

                    toolbar_title.text = "${response.body()!!.numResults.toString()} Books"
                    mBooks = response.body()!!.results!!.books!!
                    mBookAdapter = BookAdapter(mBooks)
                    book_recyclerview.layoutManager = LinearLayoutManager(this@BookListActivity)
                    book_recyclerview.adapter = mBookAdapter
                    book_recyclerview.visibility = View.VISIBLE
                } else {
                    Toast.makeText(this@BookListActivity, "No response body", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<BookListResponse>, t: Throwable) {
                swipe_refresh_layout.isRefreshing = false
                Toast.makeText(this@BookListActivity, "Oops. Please try again.", Toast.LENGTH_SHORT)
                    .show()
            }
        })
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

    companion object {
        fun createIntent(context: Context, listNameEncoded: String?): Intent {
            val intent = Intent(context, BookListActivity::class.java)
            intent.putExtra(Key.LIST_NAME_ENCODED, listNameEncoded)
            return intent
        }
    }
}