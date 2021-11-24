package client.provider;

public enum ClientType {
    
    AWS_IDLE_TIMEOUT("result/aws-idle-timeout.txt"),
    IBM_IDLE_TIMEOUT("result/ibm-idle-timeout.txt"),
    AZURE_IDLE_TIMEOUT("result/azure-idle-timeout.txt");

    private String path;
    
    ClientType(String path) {
        this.path = path;
    }
    
    public String getPath() {
        return path;
    }
}
