package com.example.myktorapp

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.response.*

fun main() {
    val server = embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }
    
    server.start(wait = true)
}

fun Application.module() {
    routing {
        get("/") {
            call.respondText("Welcome to the User API")
        }
        get("/users") {
            val users = listUsers()
            call.respond(users)
suspend fun listUsers(): List<User> {
    val userService = UserService()
    return userService.listUsers()
}

data class User(val id: String, val name: String, val email: String)
        }
    }
}
