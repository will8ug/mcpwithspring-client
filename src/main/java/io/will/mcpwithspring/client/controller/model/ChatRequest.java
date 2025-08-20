package io.will.mcpwithspring.client.controller.model;

public record ChatRequest(
        String question,
        String conversationId
) {
}
