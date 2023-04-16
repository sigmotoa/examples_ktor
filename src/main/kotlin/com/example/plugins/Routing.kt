package com.example.plugins

import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import kotlinx.serialization.Serializable

fun Application.configureRouting() {
    routing {
        get("/h1") {
            call.respondText("Hello World 0!")
        }
        route(path = "/h2", method = HttpMethod.Get) {
            handle { call.respondText("Hello World 1!") }
        }
        get("/path/{name}") {
            val name = call.parameters["name"]
            val header = call.request
            call.respondText("Hello $name ${header.headers["Connection"]}")
        }

        get("/query") {
            val name = call.request.queryParameters["name"]
            val email = call.request.queryParameters["email"]

            call.respondText("Hello $name, we will write you to $email")
        }

        get("/object") {
            try {
                val user = User("sigmotoa", 53)
                call.respond(message = user, status = HttpStatusCode.Accepted)

            } catch (e: Exception) {
                call.respond(message = "$e.message", status = HttpStatusCode.BadRequest)
            }
        }

        get("/other") {
            call.respondRedirect("/object", permanent = false)
        }
    }
}

@Serializable
data class User(
    val name: String,
    val age: Int = 30
)