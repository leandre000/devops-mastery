package com.rca.demo_course;

import com.rca.demo_course.service.CalculatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * End-to-end integration tests for the Calculator application.
 * Tests the full application context with real HTTP requests.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Calculator Application End-to-End Tests")
public class CalculatorApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CalculatorService calculatorService;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/calculator";
    }

    @Test
    @DisplayName("Application context should load successfully")
    void contextLoads() {
        assertNotNull(calculatorService, "Calculator service should be autowired");
    }

    @Test
    @DisplayName("Add endpoint should work with real service")
    void testAddEndpointE2E() {
        String url = getBaseUrl() + "/add?a=2.0&b=3.0";

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        Map<String, Object> body = response.getBody();
        assertEquals(2.0, body.get("a"));
        assertEquals(3.0, body.get("b"));
        assertEquals(5.0, body.get("result"));
        assertEquals("addition", body.get("operation"));
    }

    @Test
    @DisplayName("Subtract endpoint should work with real service")
    void testSubtractEndpointE2E() {
        String url = getBaseUrl() + "/subtract?a=5.0&b=2.0";

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Object> body = response.getBody();
        assertEquals(3.0, body.get("result"));
        assertEquals("subtraction", body.get("operation"));
    }

    @Test
    @DisplayName("Multiply endpoint should work with real service")
    void testMultiplyEndpointE2E() {
        String url = getBaseUrl() + "/multiply?a=4.0&b=3.0";

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Object> body = response.getBody();
        assertEquals(12.0, body.get("result"));
        assertEquals("multiplication", body.get("operation"));
    }

    @Test
    @DisplayName("Divide endpoint should work with real service")
    void testDivideEndpointE2E() {
        String url = getBaseUrl() + "/divide?a=10.0&b=2.0";

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Object> body = response.getBody();
        assertEquals(5.0, body.get("result"));
        assertEquals("division", body.get("operation"));
    }

    @Test
    @DisplayName("Divide endpoint should handle division by zero")
    void testDivideByZeroE2E() {
        String url = getBaseUrl() + "/divide?a=5.0&b=0.0";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    @DisplayName("Power endpoint should work with real service")
    void testPowerEndpointE2E() {
        String url = getBaseUrl() + "/power?base=2.0&exponent=3.0";

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Object> body = response.getBody();
        assertEquals(8.0, body.get("result"));
        assertEquals("power", body.get("operation"));
    }

    @Test
    @DisplayName("Square root endpoint should work with real service")
    void testSquareRootEndpointE2E() {
        String url = getBaseUrl() + "/sqrt?number=9.0";

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Object> body = response.getBody();
        assertEquals(3.0, body.get("result"));
        assertEquals("square root", body.get("operation"));
    }

    @Test
    @DisplayName("Square root endpoint should handle negative numbers")
    void testSquareRootNegativeE2E() {
        String url = getBaseUrl() + "/sqrt?number=-1.0";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    @DisplayName("Absolute endpoint should work with real service")
    void testAbsoluteEndpointE2E() {
        String url = getBaseUrl() + "/abs?number=-5.0";

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Object> body = response.getBody();
        assertEquals(5.0, body.get("result"));
        assertEquals("absolute value", body.get("operation"));
    }

    @Test
    @DisplayName("Percentage endpoint should work with real service")
    void testPercentageEndpointE2E() {
        String url = getBaseUrl() + "/percentage?number=100.0&percentage=20.0";

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Object> body = response.getBody();
        assertEquals(20.0, body.get("result"));
        assertEquals("percentage", body.get("operation"));
    }

    @Test
    @DisplayName("All endpoints should be accessible")
    void testAllEndpointsAccessible() {
        String[] endpoints = {
                "/add?a=1&b=1",
                "/subtract?a=2&b=1",
                "/multiply?a=2&b=2",
                "/divide?a=4&b=2",
                "/power?base=2&exponent=2",
                "/sqrt?number=4",
                "/abs?number=-3",
                "/percentage?number=100&percentage=10"
        };

        for (String endpoint : endpoints) {
            String url = getBaseUrl() + endpoint;
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            assertEquals(HttpStatus.OK, response.getStatusCode(),
                    "Endpoint " + endpoint + " should be accessible");
            assertNotNull(response.getBody(),
                    "Response body should not be null for " + endpoint);
        }
    }

    @Test
    @DisplayName("Application should handle concurrent requests")
    void testConcurrentRequests() throws InterruptedException {
        String url = getBaseUrl() + "/add?a=1.0&b=1.0";

        // Simulate concurrent requests
        Thread[] threads = new Thread[10];
        ResponseEntity<Map>[] responses = new ResponseEntity[10];

        for (int i = 0; i < 10; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                responses[index] = restTemplate.getForEntity(url, Map.class);
            });
            threads[i].start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }

        // Verify all responses are successful
        for (int i = 0; i < 10; i++) {
            assertEquals(HttpStatus.OK, responses[i].getStatusCode(),
                    "Concurrent request " + i + " should be successful");
            assertEquals(2.0, responses[i].getBody().get("result"),
                    "Result should be correct for concurrent request " + i);
        }
    }

    @Test
    @DisplayName("Service should handle edge cases correctly")
    void testServiceEdgeCasesE2E() {
        // Test with very large numbers
        String url1 = getBaseUrl() + "/add?a=" + Double.MAX_VALUE + "&b=0";
        ResponseEntity<Map> response1 = restTemplate.getForEntity(url1, Map.class);
        assertEquals(HttpStatus.OK, response1.getStatusCode());

        // Test with very small numbers
        String url2 = getBaseUrl() + "/multiply?a=" + Double.MIN_VALUE + "&b=1";
        ResponseEntity<Map> response2 = restTemplate.getForEntity(url2, Map.class);
        assertEquals(HttpStatus.OK, response2.getStatusCode());

        // Test percentage with zero
        String url3 = getBaseUrl() + "/percentage?number=100&percentage=0";
        ResponseEntity<Map> response3 = restTemplate.getForEntity(url3, Map.class);
        assertEquals(HttpStatus.OK, response3.getStatusCode());
        assertEquals(0.0, response3.getBody().get("result"));
    }
}
