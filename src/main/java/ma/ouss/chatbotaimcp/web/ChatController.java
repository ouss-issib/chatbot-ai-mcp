package ma.ouss.chatbotaimcp.web;

import ma.ouss.chatbotaimcp.agents.AiAgent;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {
    private AiAgent aiAgent;

    public ChatController(AiAgent aiAgent) {
        this.aiAgent = aiAgent;
    }

    //@RequestParam String query Optional
    @GetMapping(value = "/chat",produces = MediaType.TEXT_PLAIN_VALUE)
    public Flux<String> chat(String query){
        return aiAgent.askAgent(query);
    }
}
