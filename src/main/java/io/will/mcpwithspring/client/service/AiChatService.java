package io.will.mcpwithspring.client.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AiChatService {
    private final ChatClient chatClient;

    private final SimpleLoggerAdvisor simpleLoggerAdvisor = new SimpleLoggerAdvisor();

    public AiChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public Flux<String> streamChat(String userInput) {
        return chatClient.prompt()
                .advisors(simpleLoggerAdvisor)
                .user(userInput)
                .stream()
                .content();
    }

    public Flux<String> streamChatInConversation(String userInput, String conversationId) {
        return chatClient.prompt()
                .advisors(simpleLoggerAdvisor)
                .advisors(spec -> spec.param(ChatMemory.CONVERSATION_ID, conversationId))
                .user(userInput)
                .stream()
                .content();
    }
}
