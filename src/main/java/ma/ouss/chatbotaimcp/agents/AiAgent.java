package ma.ouss.chatbotaimcp.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Component
public class AiAgent {
    private ChatClient chatClient;

    public AiAgent(ChatClient.Builder builder, ChatMemory memory, ToolCallbackProvider tools) {
        Arrays.stream(tools.getToolCallbacks()).forEach(t-> System.out.println(t.getToolDefinition()));
        this.chatClient = builder
                .defaultSystem("""
                        Vous êtes un agent qui se charge de répondre aux questions des utilisateurs 
                        en fonction de leur contexte.Si aucun contexte n'est fourni , répond avec JE NE SAIS PAS :).
                        """)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(memory).build()
                )
                .defaultToolCallbacks(tools)
                .build();
    }

    public Flux<String> askAgent(String query){
        return chatClient.prompt()
                .user(query)
                .stream().content();
    }
}
