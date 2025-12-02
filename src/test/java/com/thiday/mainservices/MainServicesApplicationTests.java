package com.thiday.mainservices;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Basic integration test to verify Spring Boot context loads successfully.
 */
@SpringBootTest
@TestPropertySource(properties = {
    "spring.data.mongodb.uri=mongodb://localhost:27017/test",
    "spring.data.mongodb.database=test-db"
})
class MainServicesApplicationTests {

    @Test
    void contextLoads() {
        // This test ensures the Spring Boot application context loads without errors
    }
}



