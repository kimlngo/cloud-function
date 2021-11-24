package response.extractor;

import java.net.http.HttpResponse;

import com.google.gson.Gson;

import response.ibm.IbmPerformanceResponse;
import response.ibm.IbmResponse;

/**
 * @author kimlngo
 * For IBM, we can't use this response because the returned response is wrapped with other details
 * so we need specific response extractor for IBM
 */
public class IbmResponseExtractor implements IResponseExtractor {
    // activationId - hostId
    private static final String EXTRACT_PATTERN = "%s\t%s";
    private static final Gson GSON = new Gson();
    
    @Override
    @SuppressWarnings("unchecked")
    public String extractResponse(HttpResponse<String> httpResponse) throws Exception {
        IbmResponse<IbmPerformanceResponse> ibmResponse = GSON.fromJson(httpResponse.body(), IbmResponse.class);
        ibmResponse.setType(IbmPerformanceResponse.class);
        
        String activationId = ibmResponse.getActivationId();
        
        IbmPerformanceResponse innerResponse = ibmResponse.getResult();
        String hostId = innerResponse.getHostId();
            
        return String.format(EXTRACT_PATTERN, activationId, hostId);
    }

}
