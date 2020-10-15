package io.rede.bookfront.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Build Retrofit instance with Base URL for all calls & the converter factory to be used
object RetrofitInstance {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://api.nytimes.com/svc/books/v3/lists/"
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().apply {
                    baseUrl(BASE_URL)
                    addConverterFactory(GsonConverterFactory.create())
                }.build()
            }
            return retrofit
        }
}