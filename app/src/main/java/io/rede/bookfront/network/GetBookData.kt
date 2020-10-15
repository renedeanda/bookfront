package io.rede.bookfront.network

import io.rede.bookfront.Env
import io.rede.bookfront.model.BookListResponse
import io.rede.bookfront.model.BookListsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetBookData {
    @GET("names.json?api-key=" + Env.API_KEY)
    fun getTopBookLists(): Call<BookListsResponse>

    @GET("{listNameEncoded}.json?api-key=" + Env.API_KEY)
    fun getTopBooks(@Path("listNameEncoded") listNameEncoded: String?): Call<BookListResponse>
}