package com.example.servicea

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate


internal class RemoteGreeterTest {

    val template = RestTemplate()
    val cut = RemoteGreeter(template)

    val wireMock = WireMockServer(8081)

    @BeforeEach fun startServer() {
        wireMock.start()
    }

    @AfterEach fun stopServer() {
        wireMock.stop()
    }

    @Test fun abc() {
        wireMock.givenThat(get(urlEqualTo("/greeting?name=World"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "message": "Hello World!"
                            }
                            """)))
        val message = cut.sayHello()
        assertThat(message).isEqualTo("Hello World!")
    }

}