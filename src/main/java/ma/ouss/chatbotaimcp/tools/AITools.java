package ma.ouss.chatbotaimcp.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class AITools {
    @Tool(name = "GetEmploye",
            description = "Find details Infos about the given Employee ")
    public Employee getEmployee(@ToolParam(description = "The Employee name") String name){
        return new Employee(name,12000,25,10);
    }
}


record Employee(String name,double salary , int age,int seniority){}
