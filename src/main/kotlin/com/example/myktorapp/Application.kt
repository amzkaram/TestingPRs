package com.example.myktorapp

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    routing {
        get("/") {
            call.respondText("Hello, World!", ContentType.Text.Plain)
        }
        post("/users") {
            handlePostUser(call)
        }
    }
import com.example.myktorapp.UserController.handlePostUser
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
}
