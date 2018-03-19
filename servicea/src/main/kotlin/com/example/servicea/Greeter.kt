package com.example.servicea

import org.springframework.stereotype.Component

@Component
class Greeter {

    fun sayHello(name: String = "World"): String {
        return "Hello $name!"
    }

}