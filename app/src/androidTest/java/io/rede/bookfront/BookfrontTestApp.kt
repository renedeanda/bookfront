package io.rede.bookfront

class BookfrontTestApp : BookfrontApp() {

    override fun getBaseUrl(): String {
        return "http://127.0.0.1:8080"
    }

}