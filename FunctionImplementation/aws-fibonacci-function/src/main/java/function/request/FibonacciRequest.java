package function.request;

public class FibonacciRequest {
    private int nthNumber;
    
    public FibonacciRequest(int nthNumber) {
        this.nthNumber = nthNumber;
    }

    public int getNthNumber() {
        return nthNumber;
    }

    public void setNthNumber(int nthNumber) {
        this.nthNumber = nthNumber;
    }

    @Override
    public String toString() {
        return "FibonacciRequest [nthNumber=" + nthNumber + "]";
    }
}
