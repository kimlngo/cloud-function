package util;

import java.util.Base64;

public class Constant {
    public static final long ONE_MINUTE_INTERVAL = 60000L;
    public static final String AWS_FIBONACCI_URI = "https://<API_GATEWAY_ID>.execute-api.us-east-2.amazonaws.com/FibonacciFunction";
    public static final String IBM_FIBONACCI_URI = "https://us-east.functions.cloud.ibm.com/api/v1/namespaces/<NAME_SPACE>/actions/fibonacci-function?blocking=true";
    public static final String AZURE_FIBONACCI_URI = "https://<FUNCTION_NAME>.azurewebsites.net/api/FibonacciFunction";
    
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final int FIBONACCI_TEST_WORKLOAD = 38;
    public static final long ZERO_DELAY = 0L;
    
    //IBM Credentials
    public static final String AUTHORIZATION = "Authorization";
    private static final String IBM_API_KEY = "<NAME_SPACE_API_KEY>";
    public static final String IBM_AUTHORIZATION_KEY = "Basic " + Base64.getEncoder().encodeToString(Constant.IBM_API_KEY.getBytes());

    private Constant() {}
}