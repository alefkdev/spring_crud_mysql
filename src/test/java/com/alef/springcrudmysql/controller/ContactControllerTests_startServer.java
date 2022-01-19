package com.alef.springcrudmysql.controller;

import com.alef.springcrudmysql.model.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


/***
 * This class of tests needs to start the server to work.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ContactControllerTests_startServer {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;


    //this method tests a get request comparing the result to an object already saved in db during the initialization
    @Test
    void getContactTest(){

        Contact contact1 = new Contact();
        contact1.setId(1L);
        contact1.setName("Contact 1");
        contact1.setPhone("(111) 111-1111");
        contact1.setEmail("contact1@email.com");

        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/contacts/1", Contact.class)).isEqualTo(contact1);
    }

}
