package com.example.servicea

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController(
        private val greeter: Greeter
) {

    @GetMapping("/greeting")
    fun get(@RequestParam(required = false) name: String?): Map<String, Any> {

        val message = if (name != null) {
            greeter.sayHello(name)
        } else {
            greeter.sayHello()
        }

        return mapOf("message" to message)
    }

}