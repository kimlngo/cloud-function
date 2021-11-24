package com.kimlngo.fibonacci;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kimlngo.request.FibonacciRequest;
import com.kimlngo.response.FibonacciResponse;
import com.kimlngo.util.FunctionHostId;

public class FibonacciFunction {
    private static final Gson GSON = new Gson();
    private static final String FUNCTION_NAME = "FibonacciFunction";
    private static final String EMPTY = "";

    public static JsonObject process(JsonObject args) throws Exception {

        try {
            long startTime = System.currentTimeMillis();
            FibonacciRequest request = GSON.fromJson(GSON.toJson(args), FibonacciRequest.class);
            int nthNumber = request.getNthNumber();
            String hostId = FunctionHostId.getHostId();

            System.out.println(String.format("numIteration = %d", nthNumber));

            int result = fib(nthNumber);
            System.out.println("result = " + result);

            FibonacciResponse response = new FibonacciResponse(FUNCTION_NAME, EMPTY, hostId, nthNumber, result, System.currentTimeMillis() - startTime);
            return GSON.toJsonTree(response).getAsJsonObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static int fib(int n) {
        if (n <= 2) {
            return 1;
        }

        return fib(n - 1) + fib(n - 2);
    }
}
