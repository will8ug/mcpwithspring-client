package io.will.mcpwithspring.client;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/client")
public class McpClientController {

    private final McpClientService mcpClientService;

    @Autowired
    public McpClientController(McpClientService mcpClientService) {
        this.mcpClientService = mcpClientService;
    }

    @GetMapping("/test")
    public Mono<String> testConnection() {
        return mcpClientService.testConnection();
    }

    @GetMapping("/weather/{city}")
    public Mono<String> getWeather(@PathVariable String city) {
        return mcpClientService.getWeather(city);
    }

    @GetMapping("/weather/{city}/summary")
    public Mono<String> getWeatherSummary(@PathVariable String city) {
        return mcpClientService.getWeatherSummary(city);
    }
} 