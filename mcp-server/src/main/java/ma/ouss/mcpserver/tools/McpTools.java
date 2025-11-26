package ma.ouss.mcpserver.tools;

import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class McpTools {
        @McpTool(name = "GetEmploye",
                description = "Find details Infos about the given Employee ")
        public Employee getEmployee(@McpArg(description = "The Employee name") String name){
            return new Employee(name,12000,25,10);
        }

        @McpTool(name = "GetListEmployees",
                description = "Get list employee infos")
        public List<Employee> getListEmployees(){
            return List.of(new Employee("Oussama",12000,27,10),
                    new Employee("Zouita",15000,29,12)
                    ,new Employee("Nizar",11000,20,2));
        }
    }

    record Employee(String name,double salary , int age,int seniority){}

