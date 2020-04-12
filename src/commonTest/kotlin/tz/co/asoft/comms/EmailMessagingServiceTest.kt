package tz.co.asoft.comms

import tz.co.asoft.email.Email
import tz.co.asoft.email.entities.EmailMessage
import tz.co.asoft.persist.dao.cache
import tz.co.asoft.persist.repo.CachedRepo
import tz.co.asoft.sendgrid.SendGridEmailGateway
import tz.co.asoft.test.AsyncTest
import kotlin.test.Test

class EmailMessagingServiceTest : AsyncTest() {
    val repo = CachedRepo<EmailMessage>(cache())
    val emailService = SendGridEmailGateway("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", repo)

    @Test
    fun should_send_test_mail() = asyncTest {
        emailService.send(
                "luge@test.com",
                listOf(Email("andylamax@programmer.net")),
                "Plain Test Email",
                "This is A Plain Test",
        )

        emailService.send(
                "luge@test.com",
                listOf(Email("andylamax@programmer.net")),
                "HTML Test Email",
                "<h1>Testing Testing, 123 Testing</h1>This is A Plain Test",
        )
    }
}