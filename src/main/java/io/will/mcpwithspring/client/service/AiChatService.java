package io.will.mcpwithspring.client.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AiChatService {
    private final ChatClient chatClient;

    public AiChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public Flux<String> streamChat(String userInput) {
        return chatClient.prompt()
                .advisors(new SimpleLoggerAdvisor())
                .user(userInput)
                .stream()
                .content();
    }
}
