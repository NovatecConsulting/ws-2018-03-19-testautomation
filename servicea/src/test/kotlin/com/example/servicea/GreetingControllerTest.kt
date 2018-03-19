package com.example.servicea

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.*
import org.mockito.BDDMockito.*
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
@ExtendWith(SpringExtension::class)
class GreetingControllerTest {

    @Autowired lateinit var mockMvc: MockMvc
    @MockBean lateinit var greeter: Greeter

    @BeforeEach
    fun defineBehaviour(){
        given(greeter.sayHello()).willReturn("foo")
        given(greeter.sayHello(Mockito.anyString())).willReturn("bar")
    }

    @Test fun `greeting with default name`() {
        mockMvc.perform(get("/greeting"))
                .andExpect(status().is2xxSuccessful)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("""
                    {
                        "message": "foo"
                    }
                    """))
        verify(greeter).sayHello()
    }

    @Test fun `greeting is custom name`() {
        mockMvc.perform(get("/greeting?name=John"))
                .andExpect(status().is2xxSuccessful)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("""
                    {
                        "message": "bar"
                    }
                    """))
        verify(greeter).sayHello("John")
    }

}