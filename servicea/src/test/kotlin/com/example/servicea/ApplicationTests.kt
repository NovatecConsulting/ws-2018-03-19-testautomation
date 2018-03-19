package com.example.servicea

import io.restassured.RestAssured
import org.hamcrest.core.IsEqual.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
class ApplicationTests {

    @MockBean lateinit var greeter: RemoteGreeter

    @LocalServerPort
    fun setPort(port: Int) {
        RestAssured.port = port
    }

    @Test
    fun `gretings without parameter`() {

        RestAssured.`when`()
                .get("/greeting")
                .then()
                .header("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body("message", equalTo("Hello World!"))


    }

    @Test
    fun `greetings with parameter`() {

        RestAssured.`when`()
                .get("/greeting?name=John")
                .then()
                .header("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body("message", equalTo("Hello John!"))


    }

}
