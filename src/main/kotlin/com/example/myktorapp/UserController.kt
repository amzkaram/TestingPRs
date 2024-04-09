package com.example.myktorapp

import io.ktor.http.*
import io.ktor.server.application.*
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.*

data class User(
    val email: String,
    val name: String,
    val dateOfBirth: String
)

suspend fun storeUser(user: User) {
    val dynamoDbClient = DynamoDbClient.create()
    val putItemRequest = PutItemRequest.builder()
        .tableName("Users")
        .item(
            mapOf(
                "email" to AttributeValue.builder().s(user.email).build(),
                "name" to AttributeValue.builder().s(user.name).build(),
                "dateOfBirth" to AttributeValue.builder().s(user.dateOfBirth).build()
            )
        )
        .build()
    dynamoDbClient.putItem(putItemRequest)
}

suspend fun handlePostUser(call: ApplicationCall) {
    val user = call.receive<User>()
    storeUser(user)
    call.respond(HttpStatusCode.Created)
}
