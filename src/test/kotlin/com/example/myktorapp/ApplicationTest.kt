package com.example.myktorapp

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun `test get root route`() = testApplication {
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Hello, World!", response.bodyAsText())
    }

    @Test
    fun `test post user`() = testApplication {
        val user = User("test@example.com", "Test User", "1990-01-01")
        val response = client.post("/users") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }
        assertEquals(HttpStatusCode.Created, response.status)
    }
}

import com.example.myktorapp.UserController.User
