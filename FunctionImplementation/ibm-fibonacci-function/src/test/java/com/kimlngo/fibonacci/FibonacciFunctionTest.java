package com.kimlngo.fibonacci;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kimlngo.request.FibonacciRequest;
import com.kimlngo.response.FibonacciResponse;

public class FibonacciFunctionTest {
    private static final Gson GSON = new Gson();
    @Test
    public void testProcess() throws Exception {
        FibonacciRequest request = new FibonacciRequest(38);
        
        JsonObject jsonResponse = FibonacciFunction.process(GSON.toJsonTree(request).getAsJsonObject());
        
        FibonacciResponse ibmFibResponse = GSON.fromJson(GSON.toJson(jsonResponse), FibonacciResponse.class);
        
        Assert.assertEquals(ibmFibResponse.getNthNumber(), 38);
        Assert.assertEquals(ibmFibResponse.getResult(), 39088169);
        Assert.assertEquals(ibmFibResponse.getFunctionName(), "FibonacciFunction");
    }
}
