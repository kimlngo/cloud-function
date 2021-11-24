package com.kimlngo.request;

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
}
