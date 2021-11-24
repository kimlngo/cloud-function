package ibm.faas;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ibm.faas.request.HelloRequest;

public class HelloWorldFunction {
    private static final Gson GSON = new Gson();
    
    public static JsonObject process(JsonObject args) throws Exception {
        String name = "world";
        
        HelloRequest request = GSON.fromJson(GSON.toJson(args), HelloRequest.class);
        
        if(request.getName() != null && !request.getName().isEmpty()) {
            name = request.getName();
            System.out.println("Name = " + name);
        }
        
        String response = String.format("{\"message\" : \"Hello %s\"}", name);
        return new JsonParser().parse(response).getAsJsonObject();
    }
}
