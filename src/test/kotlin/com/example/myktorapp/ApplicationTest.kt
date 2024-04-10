package com.example.myktorapp

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun `test root route`() = testApplication(Application::module) {
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Hello, World!", response.bodyAsText())
    }

    @Test
    fun `test fat jar`() {
        val process = ProcessBuilder("java", "-jar", "build/libs/ktor-app-fat.jar").start()
        val output = process.inputStream.bufferedReader().readText()
        assert(output.contains("Hello, World!"))
    }
}
