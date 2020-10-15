package io.rede.bookfront.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class BookListResponse {
    @SerializedName("results")
    val results: ResultsResponse? = null
    @SerializedName("num_results")
    val numResults: Int? = null
}

class ResultsResponse {
    @SerializedName("display_name")
    val displayName: String? = null
    @SerializedName("books")
    val books: List<Book>? = null
}

@Parcelize
data class Book(
    @SerializedName("rank")
    val rank: Int?,
    @SerializedName("rank_last_week")
    val rankLastWeek: Int?,
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
    @SerializedName("book_image_width")
    val bookImageWidth: String?,
    @SerializedName("book_image_height")
    val bookImageHeight: String?,
    @SerializedName("amazon_product_url")
    val amazonProductUrl: String?
) : Parcelable