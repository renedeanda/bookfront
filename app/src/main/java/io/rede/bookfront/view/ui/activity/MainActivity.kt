package io.rede.bookfront.view.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.rede.bookfront.R
import io.rede.bookfront.model.BookList
import io.rede.bookfront.model.BookListsResponse
import io.rede.bookfront.network.GetBookData
import io.rede.bookfront.network.RetrofitInstance
import io.rede.bookfront.view.ui.adapter.BookListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_default.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

//First screen in application displaying the list of NYT booklists
class MainActivity : AppCompatActivity() {

    var mBookLists: List<BookList> = ArrayList()
    var mBookListAdapter: BookListAdapter? = null
    var mService = RetrofitInstance.retrofitInstance!!.create<GetBookData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar_title.text = getString(R.string.nyt_book_lists)
        loadBookLists()
        swipe_refresh_layout.setOnRefreshListener { loadBookLists() }
    }

    //Network call to load book lists with UI updates
    private fun loadBookLists() {
        error_view.visibility = View.GONE
        swipe_refresh_layout.isRefreshing = true
        val call: Call<BookListsResponse> = mService.getTopBookLists()
        call.enqueue(object : Callback<BookListsResponse> {
            override fun onResponse(
                call: Call<BookListsResponse>,
                response: Response<BookListsResponse>
            ) {
                swipe_refresh_layout.isRefreshing = false
                if (response.body()?.results != null) {
                    mBookLists = response.body()!!.results!!
                    mBookListAdapter = BookListAdapter(mBookLists)
                    booklist_recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
                    booklist_recyclerview.adapter = mBookListAdapter
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.no_response_body),
                        Toast.LENGTH_SHORT
                    ).show()
                    error_view.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<BookListsResponse>, t: Throwable) {
                swipe_refresh_layout.isRefreshing = false
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.oops_try_again),
                    Toast.LENGTH_SHORT
                ).show()
                error_view.visibility = View.VISIBLE
            }
        })
    }
}