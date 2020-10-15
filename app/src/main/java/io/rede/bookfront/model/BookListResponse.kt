package io.rede.bookfront.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

//Initial response serialized to ResultsResponse object
class BookListResponse {
    @SerializedName("results")
    val results: ResultsResponse? = null
}

//Response broken down into the data to be accessed for our adapter list of Books
class ResultsResponse {
    @SerializedName("display_name")
    val displayName: String? = null
    @SerializedName("books")
    val books: List<Book>? = null
}

//Model for Book object, uses Parcelize annotation to make it possible to pass a Book object between activities
@Parcelize
data class Book(
    @SerializedName("rank")
    val rank: Int?,
    @SerializedName("weeks_on_list")
    val weeksOnList: Int?,
    @SerializedName("primary_isbn10")
    val primaryIsbn10: String?,
    @SerializedName("primary_isbn13")
    val primaryIsbn13: String?,
    @SerializedName("publisher")
    val publisher: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("book_image")
    val bookImage: String?,
    @SerializedName("amazon_product_url")
    val amazonProductUrl: String?
) : Parcelable