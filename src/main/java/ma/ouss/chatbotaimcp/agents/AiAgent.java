package ma.ouss.chatbotaimcp.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

@Component
public class AiAgent {
    private ChatClient chatClient;

    public AiAgent(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public Flux<String> askAgent(String query){
        return chatClient.prompt()
                .user(query)
                .stream().content();
    }
}
