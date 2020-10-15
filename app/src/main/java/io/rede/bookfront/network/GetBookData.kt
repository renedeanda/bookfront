package io.rede.bookfront.network

import io.rede.bookfront.Env
import io.rede.bookfront.model.BookListResponse
import io.rede.bookfront.model.BookListsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

//Networking interface using Retrofit annotation to make GET calls using the NYT books api
interface GetBookData {
    //GET all the NYT bestseller booklists
    @GET("names.json?api-key=" + Env.API_KEY)
    fun getTopBookLists(): Call<BookListsResponse>

    //GET all the books in one of the NYT bestseller lists using a dynamic call with listNameEncoded
    @GET("{listNameEncoded}.json?api-key=" + Env.API_KEY)
    fun getTopBooks(@Path("listNameEncoded") listNameEncoded: String?): Call<BookListResponse>
}