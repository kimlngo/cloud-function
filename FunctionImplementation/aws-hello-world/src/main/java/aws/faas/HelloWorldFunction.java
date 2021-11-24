package aws.faas;

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

import aws.faas.request.HelloRequest;

public class HelloWorldFunction implements RequestStreamHandler{
    private static final Gson GSON = new Gson();
    
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        JSONParser parser = new JSONParser();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        
        try {
            JSONObject event = (JSONObject) parser.parse(reader);
            String name = "world";
            
            if(event.get("body") != null) {
                HelloRequest request = GSON.fromJson(String.valueOf(event.get("body")), HelloRequest.class);
                System.out.println("Name = " + request.getName());
                name = request.getName();
            }
            output.write(GSON.toJson(createResponse(name)).getBytes());
        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }

    private String createResponse(String name) {
        return String.format("{\"message\" : \"Hello %s\"}", name);
    }
}
