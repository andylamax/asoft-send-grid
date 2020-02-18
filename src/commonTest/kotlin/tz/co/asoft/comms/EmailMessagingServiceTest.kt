package tz.co.asoft.comms

import tz.co.asoft.sendgrid.EmailMessagingService
import tz.co.asoft.test.AsyncTest
import kotlin.test.Test

class EmailMessagingServiceTest : AsyncTest() {
    val emailService = EmailMessagingService("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")

    @Test
    fun should_send_test_mail() = asyncTest {
        emailService.send(
                "luge@test.com",
                listOf("andylamax@programmer.net"),
                "Plain Test Email",
                "This is A Plain Test",
                false
        )

        emailService.send(
                "luge@test.com",
                listOf("andylamax@programmer.net"),
                "HTML Test Email",
                "<h1>Testing Testing, 123 Testing</h1>This is A Plain Test",
                true
        )
    }
}