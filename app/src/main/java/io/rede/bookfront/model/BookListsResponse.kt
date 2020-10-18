package io.rede.bookfront.model

import com.google.gson.annotations.SerializedName

//Initial response for api call listing out all the NYT bestseller lists
class BookListsResponse {
    @SerializedName("results")
    val results: List<BookList>? = null
}

//Model of Booklist item to load into our adapter list of booklists
class BookList(
    @SerializedName("list_name_encoded")
    val listNameEncoded: String?,
    @SerializedName("display_name")
    val displayName: String?
)