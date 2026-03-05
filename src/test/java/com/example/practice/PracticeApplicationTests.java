package com.example.practice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Verifies that the Spring application context loads successfully.
 *
 * <p>This is the most fundamental test: if the context fails to start
 * (e.g., due to misconfigured beans or missing dependencies), the test fails.
 */
@SpringBootTest
class PracticeApplicationTests {

    @Test
    void contextLoads() {
        // If the application context starts without throwing, this test passes.
    }
}
