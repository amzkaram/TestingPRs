package com.example.myktorapp

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.coroutines.runBlocking

class ApplicationTest {

    private val userService = UserService()

    @Test
    fun testRootRoute() = testApplication {
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Welcome to the User API", bodyAsText())
        }
    }

    @Test
    fun testListUsersRoute() = testApplication {
        runBlocking {
            val users = userService.listUsers()
            assertNotNull(users)
            client.get("/users").apply {
                assertEquals(HttpStatusCode.OK, status)
                assertEquals(users, bodyAsJson())
            }
        }
    }
}
