package com.example.servicea

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class GreeterTest {

    val cut = Greeter()

    @Test fun `without parameter the default message is returned`() {
        val message = cut.sayHello()
        assertThat(message).isEqualTo("Hello World!")
    }

    @Test fun `with parameter the customized message is returned`() {
        val message = cut.sayHello("John")
        assertThat(message).isEqualTo("Hello John!")
    }

}