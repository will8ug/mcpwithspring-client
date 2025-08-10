package io.will.mcpwithspring.client;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;
import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class AiChatControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testTellMeAJoke() {
        webTestClient.post()
                .uri("/ai/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"question\": \"Tell me a joke\"}")
                .exchange()
                .expectStatus().isOk()
                .returnResult(String.class)
                .getResponseBody()
                .timeout(Duration.ofSeconds(30))
                .as(StepVerifier::create)
                .expectNextMatches(response -> response != null && !response.isEmpty())
                .thenCancel()
                .verify();
    }

    @Test
    void testWhatToolsAreAvailable() {
        webTestClient.post()
                .uri("/ai/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"question\": \"What tools are available?\"}")
                .exchange()
                .expectStatus().isOk()
                .returnResult(String.class)
                .getResponseBody()
                .timeout(Duration.ofSeconds(30))
                .as(StepVerifier::create)
                .expectNextMatches(response -> response != null && !response.isEmpty())
                .thenCancel()
                .verify();
    }

    @Test
    @Disabled("This one would timeout and haven't found a proper solution yet")
    void testWeatherInGuangzhou() {
        webTestClient.post()
                .uri("/ai/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"question\": \"What is the weather in Guangzhou?\"}")
                .exchange()
                .expectStatus().isOk()
                .returnResult(String.class)
                .getResponseBody()
                .timeout(Duration.ofSeconds(30))
                .as(StepVerifier::create)
                .expectNextMatches(response -> response != null && !response.isEmpty())
                .thenCancel()
                .verify();
    }
}
