package azure.function;

import java.io.IOException;
import java.util.Optional;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import azure.function.request.HelloRequest;

public class AzureHelloWorld {
    @FunctionName("HelloWorldFunction")
    public HttpResponseMessage process(@HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) 
    HttpRequestMessage<Optional<HelloRequest>> request, final ExecutionContext context) throws IOException {
        String name = "World";
        
        try {
            if(request.getBody().get().getName() != null  && !request.getBody().get().getName().isEmpty()) {
                name = request.getBody().get().getName();
            }
            
            String responseStr = String.format("{\"message\" : \"Hello %s\"}", name);
            context.getLogger().info(name);
            
            return request.createResponseBuilder(HttpStatus.OK).body(responseStr).build();
        } catch (Exception e) {
            e.printStackTrace();
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Bad Request").build();
        }
    }
}
