package io.rede.bookfront.network

import io.rede.bookfront.BookfrontApp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Build Retrofit instance with Base URL for all calls & the converter factory to be used
object RetrofitInstance {
    private var retrofit: Retrofit? = null
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().apply {
                    baseUrl(BookfrontApp().getBaseUrl())
                    addConverterFactory(GsonConverterFactory.create())
                }.build()
            }
            return retrofit
        }
}