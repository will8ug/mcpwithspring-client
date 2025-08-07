package io.will.mcpwithspring.client.controller;

import io.will.mcpwithspring.client.controller.model.ChatRequest;
import io.will.mcpwithspring.client.service.AiChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AiChatController {
    private final AiChatService aiChatService;

    public AiChatController(AiChatService aiChatService) {
        this.aiChatService = aiChatService;
    }

    @PostMapping("/chat")
    public Flux<String> chat(@RequestBody ChatRequest chatRequest) {
        return aiChatService.plainChat(chatRequest.question());
    }
}
