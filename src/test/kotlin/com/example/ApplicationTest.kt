package com.example

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.http.*
import com.example.plugins.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/h1").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World 0!", bodyAsText())
        }
    }
}
