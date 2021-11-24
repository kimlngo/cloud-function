package response.ibm;

public class IbmPerformanceResponse {
    private int numIteration;
    private int result;
    private String hostId;
    private boolean isSuccess;

    public IbmPerformanceResponse() {}
    
    public IbmPerformanceResponse(int numIteration, int result, boolean isSuccess, String hostId) {
        this.numIteration = numIteration;
        this.result = result;
        this.isSuccess = isSuccess;
        this.hostId = hostId;
    }

    public int getNumIteration() {
        return numIteration;
    }

    public void setNumIteration(int numIteration) {
        this.numIteration = numIteration;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}