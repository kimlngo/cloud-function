package response.extractor;

import java.net.http.HttpResponse;

public interface IResponseExtractor {
    static final String INVALID_INPUT_RESPONSE = "Invalid input Response";
    
    public String extractResponse(HttpResponse<String> httpResponse) throws Exception;
}
