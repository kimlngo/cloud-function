package function.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;

import function.request.FibonacciRequest;
import function.response.FibonacciResponse;
import function.util.AwsHostId;

public class FibonacciFunction implements RequestStreamHandler {
    private static final Gson GSON = new Gson();
    private static final String FUNCTION_NAME = "FibonacciFunction";
    private static final String BODY = "body";
    
    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        long startTime = System.currentTimeMillis();
        JSONParser parser = new JSONParser();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        
        try {
            JSONObject event = (JSONObject) parser.parse(reader);
            
            if(event.get(BODY) != null) {
                FibonacciRequest request = GSON.fromJson(event.get(BODY).toString(), FibonacciRequest.class); 
                System.out.println("request = " + request);
                
                int nthNumber = request.getNthNumber();
                int result = fib(nthNumber);
                long duration = System.currentTimeMillis() - startTime;
                
                System.out.println("result = " + result);
                System.out.println("execution duration = " + duration + "ms");
                
                FibonacciResponse response = new FibonacciResponse(FUNCTION_NAME, 
                                                                    context.getAwsRequestId(), 
                                                                    AwsHostId.getHostId(context), 
                                                                    nthNumber, result, duration);
                        
                output.write(GSON.toJson(response).getBytes());
            } else {
                output.write(createErrorResponse("request has no body").getBytes());
            }
        } catch (ParseException e) {
            output.write(createErrorResponse("exception happened. " + e.getMessage()).getBytes());
        }         
    }
    
    private int fib(int n) {
        if(n <= 2) {
            return 1;
        }
        
        return fib(n - 1) + fib(n - 2);
    }
    
    
    private String createErrorResponse(String errorMsg) {
        return String.format("{\"error\":\"%s\"}", errorMsg);
    }
}
