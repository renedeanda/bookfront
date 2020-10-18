package io.rede.bookfront

import android.app.Application

open class BookfrontApp : Application() {
    open fun getBaseUrl(): String {
        return "https://api.nytimes.com/svc/books/v3/lists/"
    }
}