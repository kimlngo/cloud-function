package client.provider;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;

import util.Constant;

public class RequestCreator {
    private static final RequestCreator INSTANCE = new RequestCreator(); 
    
    private RequestCreator() { }

    public static RequestCreator getInstance() {
        return INSTANCE;
    }
    
    public HttpRequest createRequest(ClientType clientType, String requestBody) {
        switch(clientType) {
            case AWS_IDLE_TIMEOUT:
                return HttpRequest.newBuilder(URI.create(Constant.AWS_FIBONACCI_URI))
                        .header(Constant.CONTENT_TYPE, Constant.APPLICATION_JSON)
                        .POST(BodyPublishers.ofString(requestBody))
                        .build();
                
            case IBM_IDLE_TIMEOUT:
                return HttpRequest.newBuilder(URI.create(Constant.IBM_FIBONACCI_URI))
                        .header(Constant.CONTENT_TYPE, Constant.APPLICATION_JSON)
                        .header(Constant.AUTHORIZATION, Constant.IBM_AUTHORIZATION_KEY)
                        .POST(BodyPublishers.ofString(requestBody))
                        .build();
                
            case AZURE_IDLE_TIMEOUT:
                return HttpRequest.newBuilder(URI.create(Constant.AZURE_FIBONACCI_URI))
                        .header(Constant.CONTENT_TYPE, Constant.APPLICATION_JSON)
                        .POST(BodyPublishers.ofString(requestBody))
                        .build();
                
            default:
                throw new IllegalArgumentException("Invalid ClientType." + clientType.toString());
        }
    }
}