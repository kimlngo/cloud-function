package com.kimlngo.functions;

import java.io.IOException;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kimlngo.request.FibonacciRequest;
import com.kimlngo.response.FibonacciResponse;
import com.kimlngo.util.FunctionHostId;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class FibonacciFunction {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String BAD_REQUEST = "Bad request";
    
    @FunctionName("FibonacciFunction")
    public HttpResponseMessage process(@HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) 
    HttpRequestMessage<Optional<FibonacciRequest>> request, final ExecutionContext context) throws IOException {
        StringBuilder sb = new StringBuilder();

        try {
            int nNumber = request.getBody().get().getNthNumber();
            
            sb.append(String.format("nth = %d\n", nNumber));
            sb.append(String.format("invocationId = %s\n", context.getInvocationId()));
            
            long startTime = System.currentTimeMillis();
            
            int result = fib(nNumber);
            
            long duration = System.currentTimeMillis() - startTime;
            sb.append(String.format("duration = %d" , duration));
            
            String responseStr = constructResponse(context.getFunctionName(), context.getInvocationId(), FunctionHostId.getHostId(), nNumber, result, duration);
            context.getLogger().info(sb.toString());
            
            return request.createResponseBuilder(HttpStatus.OK).body(responseStr).build();
        } catch (Exception e) {
            e.printStackTrace();
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body(BAD_REQUEST).build();
        }
    }

    private int fib(int n) {
        if(n <= 2) {
            return 1;
        }
        
        return fib(n - 1) + fib(n - 2);
    }
    
    private String constructResponse(String functionName, String invocationId, String hostId, int numberIteration, int result, long duration) {
        FibonacciResponse response = new FibonacciResponse(functionName, invocationId, hostId, numberIteration, result, duration);
        return GSON.toJson(response);
    }
}
