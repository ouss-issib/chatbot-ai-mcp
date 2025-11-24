package ma.ouss.chatbotaimcp.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

@Component
public class AiAgent {
    private ChatClient chatClient;

    public AiAgent(ChatClient.Builder builder, ChatMemory memory) {
        this.chatClient = builder
                .defaultSystem("""
                        Vous êtes un agent qui se charge de répondre aux questions des utilisateurs 
                        en fonction de leur contexte.Si aucun contexte n'est fourni , répond avec JE NE SAIS PAS :).
                        """)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(memory).build()
                )
                .build();
    }

    public Flux<String> askAgent(String query){
        return chatClient.prompt()
                .user(query)
                .stream().content();
    }
}
