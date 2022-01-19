package com.alef.springcrudmysql.controller;

import com.alef.springcrudmysql.model.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


/***
 * This class of tests uses the full spring context but without the server
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ContactControllerTests_mockMvc {

    @Autowired
    private MockMvc mockMvc;

    //it tests a get request comparing the request's result to an object already saved in db during the initialization
    @Test
    void getContactTest() throws Exception {

        Contact contact1 = new Contact();
        contact1.setId(1L);
        contact1.setName("Contact 1");
        contact1.setPhone("(111) 111-1111");
        contact1.setEmail("contact1@email.com");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(contact1);

       this.mockMvc.perform(MockMvcRequestBuilders.get("/contacts/1")).andExpect(MockMvcResultMatchers.content().json(json));
    }

}
