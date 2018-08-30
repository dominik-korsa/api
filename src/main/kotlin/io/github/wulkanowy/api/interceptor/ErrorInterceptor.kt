package io.github.wulkanowy.api.interceptor

import io.github.wulkanowy.api.auth.*
import okhttp3.Interceptor
import okhttp3.Response
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class ErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        checkForError(Jsoup.parse(response.peekBody(Long.MAX_VALUE).string()))

        return response
    }

    private fun checkForError(doc: Document) {
        when(doc.title()) {
            "Błąd" -> throw VulcanException(doc.body().text())
            "Błąd strony" -> throw VulcanException(doc.select(".errorMessage").text())
            "Logowanie" -> throw AccountPermissionException(doc.select("div").last().html().split("<br>")[1].trim())
            "Dziennik UONET+" -> throw NotLoggedInException(doc.select(".loginButton").text())
            "Przerwa techniczna" -> throw ServiceUnavailableException(doc.title())
        }

        doc.select(".ErrorMessage, #ErrorTextLabel").let {
            if (it.isNotEmpty()) throw BadCredentialsException(it.text())
        }

        doc.select("#MainPage_ErrorDiv div").let {
            if (it.isNotEmpty()) throw VulcanException(it[0].ownText())
        }

        doc.select("h2.error").let {
            if (it.isNotEmpty()) throw AccountPermissionException(it.text())
        }
    }
}