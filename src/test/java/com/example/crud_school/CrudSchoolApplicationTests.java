package com.example.crud_school;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
class CrudSchoolApplicationTests {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void contextLoads() {
        // This test will pass if the application context loads successfully
    }

    @Test
    void testApplicationIsRunning() {
        String url = "http://localhost:" + port + "/api/test";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("School Database CRUD Application is running!"));
    }

    @Test
    void testHealthEndpoint() {
        String url = "http://localhost:" + port + "/api/test/health";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("Application is healthy!"));
    }

    @Test
    void testSchoolsEndpoint() {
        String url = "http://localhost:" + port + "/api/schools";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Should return an empty array or schools list
        assertNotNull(response.getBody());
    }
}
