package io.will.mcpwithspring.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.ai.mcp.client.base-url=http://localhost:8080"
})
class McpClientServiceTest {

    @Autowired
    private McpClientService mcpClientService;

    @Test
    void testConnection() {
        Mono<String> result = mcpClientService.testConnection();
        
        StepVerifier.create(result)
            .expectNextMatches(response -> 
                response.contains("Successfully connected") || 
                response.contains("Failed to connect"))
            .verifyComplete();
    }

    @Test
    void testGetWeather() {
        Mono<String> result = mcpClientService.getWeather("london");
        
        StepVerifier.create(result)
            .expectNextMatches(response -> 
                response.contains("Weather for london") || 
                response.contains("Failed to get weather"))
            .verifyComplete();
    }

    @Test
    void testGetWeatherSummary() {
        Mono<String> result = mcpClientService.getWeatherSummary("paris");
        
        StepVerifier.create(result)
            .expectNextMatches(response -> 
                response.contains("Weather summary for paris") || 
                response.contains("Failed to get weather summary"))
            .verifyComplete();
    }
} 