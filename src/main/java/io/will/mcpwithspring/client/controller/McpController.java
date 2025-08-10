package io.will.mcpwithspring.client.controller;

import io.will.mcpwithspring.client.service.McpClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mcp")
public class McpController {
    private final McpClientService mcpClientService;

    public McpController(McpClientService mcpClientService) {
        this.mcpClientService = mcpClientService;
    }

    @PostMapping("/examine-tools")
    public void listTools() {
        mcpClientService.examineMcpClients();
    }
}
