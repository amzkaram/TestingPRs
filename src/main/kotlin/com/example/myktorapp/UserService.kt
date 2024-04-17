package com.example.myktorapp

import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import aws.sdk.kotlin.services.dynamodb.model.ScanRequest

class UserService {
    private val dynamoDbClient = DynamoDbClient { region = "us-west-2" }
    private val usersTable = dynamoDbClient.table("users")

    suspend fun listUsers(): List<User> {
        val scanRequest = ScanRequest.builder().tableName(usersTable.tableName).build()
        val scanResponse = dynamoDbClient.scanPaginated(scanRequest)
        return scanResponse.mapNotNull { it.toUser() }
    }

    private fun Map<String, AttributeValue>.toUser(): User? {
        val id = this["id"]?.s ?: return null
        val name = this["name"]?.s ?: return null
        val email = this["email"]?.s ?: return null
        return User(id, name, email)
    }
}
