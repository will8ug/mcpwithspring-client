package io.will.mcpwithspring.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class McpwithspringClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpwithspringClientApplication.class, args);
    }

    @Value("${ai.user.input}")
    private String userInput;

    @Bean
    public CommandLineRunner predefinedQuestions(ChatClient chatClient, ConfigurableApplicationContext context) {
        return args -> {
            System.out.println("\n QUESTION: " + userInput);
            System.out.println("\n ASSISTANT: " + chatClient
                    .prompt(userInput)
                    .advisors(new SimpleLoggerAdvisor())
                    .call()
                    .content()
            );

            context.close();
        };
    }
}
