package com.example.servicea

import com.fasterxml.jackson.databind.JsonNode
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class RemoteGreeter(
        private val template: RestTemplate
) {

    fun sayHello(name: String = "World"): String {
        val responseEntity = template.getForEntity("http://localhost:8081/greeting?name=$name", JsonNode::class.java)
        return responseEntity.body?.get("message")?.textValue() ?: error("messag property not found: ${responseEntity.body}")
    }

}