package response;

/**
 * @author kimlngo
 * This is a common direct response from AWS and Azure Fibonacci Cloud Function
 * For IBM, we can't use this response because the returned response is wrapped with other details
 * so we need specific response extractor for IBM
 */
public class CommonFibonacciResponse {
    private String functionName;
    private String invocationId;
    private String hostId;
    private int nthNumber;
    private int result;
    private long executionDuration;

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getInvocationId() {
        return invocationId;
    }

    public void setInvocationId(String invocationId) {
        this.invocationId = invocationId;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public int getNthNumber() {
        return nthNumber;
    }

    public void setNthNumber(int nthNumber) {
        this.nthNumber = nthNumber;
    }

    public long getExecutionDuration() {
        return executionDuration;
    }

    public void setExecutionDuration(long executionDuration) {
        this.executionDuration = executionDuration;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

}
