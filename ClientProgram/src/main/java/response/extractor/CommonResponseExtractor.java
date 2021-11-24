package response.extractor;

import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import response.CommonFibonacciResponse;

/**
 * @author kimlngo
 * HTTPResponse:
    {
      "functionName": "FibonacciFunction",
      "invocationId": "13a42b77-b3d5-4cfc-9cbf-1eff1ea10e90",
      "hostId": "097351",
      "nthNumber": 38,
      "result": 39088169,
      "executionDuration": 261
    }
 */
public class CommonResponseExtractor implements IResponseExtractor {
    //invocationId (String) - hostId (String)
    private static final String RESPONSE_PATTERN = "%s\t%s";
    private static final Gson GSON = new Gson();
    
    @Override
    public String extractResponse(HttpResponse<String> httpResponse) throws Exception {
        
        if(JsonParser.parseString(httpResponse.body()).isJsonObject()) {
            CommonFibonacciResponse response = GSON.fromJson(httpResponse.body(), CommonFibonacciResponse.class);
            
            return String.format(RESPONSE_PATTERN, response.getInvocationId(), response.getHostId());
        }
        
        return INVALID_INPUT_RESPONSE;
    }

}
