package tz.co.asoft.sendgrid

import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import tz.co.asoft.comms.EmailMessagingService as EmailService

class EmailMessagingService(private val apiKey: String) : EmailService {
    private val client = HttpClient { }
    private val url = "https://api.sendgrid.com/v3/mail/send"
    private fun content(sender: String, receivers: List<String>, subject: String?, body: String, type: String): String {
        val cntnt = """
        {
          "personalizations": [
            {
              "to": [${receivers.joinToString(",") { """{ "email":"$it"}""" }}]
            }
          ],
          "from": {
            "email": "$sender"
          },
          "subject": "${subject ?: "No Subject"}",
          "content": [
            {
              "type": "$type",
              "value": "$body"
            }
          ]
        }
    """.trimIndent()
        return cntnt
    }

    override suspend fun send(sender: String, receivers: List<String>, subject: String?, body: String, hasHtmlContent: Boolean) {
        client.post<String>(url) {
            header("Authorization", "Bearer $apiKey")
            contentType(ContentType.Application.Json)
            val type = if (hasHtmlContent) "text/html" else "text/plain"
            this.body = content(sender, receivers, subject, body, type)
        }
    }
}