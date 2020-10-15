package io.rede.bookfront.model

import com.google.gson.annotations.SerializedName

class BookListsResponse {
    @SerializedName("results")
    val results: List<BookList>? = null
    @SerializedName("num_results")
    val numResults: Int? = null
}

class BookList {
    @SerializedName("list_name_encoded")
    val listNameEncoded:String? = null
    @SerializedName("display_name")
    val displayName: String? = null
}