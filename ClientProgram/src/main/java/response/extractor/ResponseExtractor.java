package response.extractor;

import client.provider.ClientType;

public class ResponseExtractor {
    private static final ResponseExtractor INSTANCE = new ResponseExtractor();
    private ResponseExtractor() {}
    
    public static ResponseExtractor getInstance() {
        return INSTANCE;
    }
    
    public IResponseExtractor getExtractor(ClientType clientType) {
        switch (clientType) {
        case AWS_IDLE_TIMEOUT:
        case AZURE_IDLE_TIMEOUT:
            return new CommonResponseExtractor();
        case IBM_IDLE_TIMEOUT:
            return new IbmResponseExtractor();
            
        
            
            default:
                throw new IllegalArgumentException("Invalid ClientType." + clientType.toString());
        }
    }
}
