package io.will.mcpwithspring.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

@Service
public class McpClientService {

    private static final Logger logger = LoggerFactory.getLogger(McpClientService.class);
    
    private final WebClient webClient;
    
    @Value("${spring.ai.mcp.client.base-url:http://localhost:8080}")
    private String baseUrl;

    public McpClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<String> testConnection() {
        logger.info("Testing MCP server connection...");
        return webClient.get()
            .uri(baseUrl + "/api/weather/london")
            .retrieve()
            .bodyToMono(String.class)
            .map(response -> {
                logger.info("Successfully connected to MCP server. Response: {}", response);
                return "Successfully connected to MCP server. Response: " + response;
            })
            .onErrorResume(error -> {
                logger.error("Failed to connect to MCP server", error);
                return Mono.just("Failed to connect to MCP server: " + error.getMessage());
            });
    }

    public Mono<String> getWeather(String city) {
        logger.info("Getting weather for city: {}", city);
        return webClient.get()
            .uri(baseUrl + "/api/weather/" + city)
            .retrieve()
            .bodyToMono(String.class)
            .map(response -> {
                logger.info("Weather result: {}", response);
                return "Weather for " + city + ": " + response;
            })
            .onErrorResume(error -> {
                logger.error("Failed to get weather for {}", city, error);
                return Mono.just("Failed to get weather: " + error.getMessage());
            });
    }

    public Mono<String> getWeatherSummary(String city) {
        logger.info("Getting weather summary for city: {}", city);
        return webClient.get()
            .uri(baseUrl + "/api/weather/" + city + "/summary")
            .retrieve()
            .bodyToMono(String.class)
            .map(response -> {
                logger.info("Weather summary result: {}", response);
                return "Weather summary for " + city + ": " + response;
            })
            .onErrorResume(error -> {
                logger.error("Failed to get weather summary for {}", city, error);
                return Mono.just("Failed to get weather summary: " + error.getMessage());
            });
    }
} 