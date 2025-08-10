package io.will.mcpwithspring.client.service;

import io.modelcontextprotocol.client.McpAsyncClient;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class McpClientService {
    @Autowired
    private List<McpAsyncClient> mcpClients;

    public void examineMcpClients() {
        mcpClients.forEach(client -> {
            Mono<McpSchema.ListToolsResult> listToolsResult = client.listTools();
            System.out.println(listToolsResult);
        });
    }
}
