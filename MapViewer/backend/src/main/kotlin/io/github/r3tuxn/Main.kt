package io.github.r3tuxn

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*

fun main() {
    embeddedServer(Netty, port = 9090, host = "0.0.0.0") {
        install(CORS) {
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            method(HttpMethod.Delete)
            anyHost()
        }
        install(Compression) {
            gzip()
        }

        routing {
            get("/") {
                //call.respondText("Hello World!")
            }
            // Static plugin. Try to access `/static/index.html`
            static("/static") {
                resources("static")
            }
        }
    }.start(wait = true)
}
