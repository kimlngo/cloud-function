package function.response;

public class FibonacciResponse {
    private String functionName;
    private String invocationId;
    private String hostId;
    private int nthNumber;
    private int result;
    private long executionDuration;

    public FibonacciResponse(String functionName, String invocationId, String hostId, int nthNumber, int result, long executionDuration) {
        this.functionName = functionName;
        this.invocationId = invocationId;
        this.hostId = hostId;
        this.nthNumber = nthNumber;
        this.result = result;
        this.executionDuration = executionDuration;
    }

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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public long getExecutionDuration() {
        return executionDuration;
    }

    public void setExecutionDuration(long executionDuration) {
        this.executionDuration = executionDuration;
    }
}
