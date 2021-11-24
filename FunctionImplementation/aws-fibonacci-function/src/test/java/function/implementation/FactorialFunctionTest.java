package function.implementation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.easymock.EasyMock;
import org.powermock.api.easymock.PowerMock;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;

import function.request.FibonacciRequest;
import function.response.FibonacciResponse;

public class FactorialFunctionTest {
    private static final String RAW_HOST_ID = "2021/05/06/[$LATEST]4c56f98889154129b0850cbdf94c2b47";
    private static final String HOST_ID = "4c56f98889154129b0850cbdf94c2b47";
    private static final String REQUEST_ID = "519c05ac-d424-480f-9ec4-a46ecacfb97e";
    private static final Gson GSON = new Gson();
    private static final String FUNCTION_NAME = "FibonacciFunction";
    private static final int RESULT = 39088169;
    private static final int NTH_NUMBER = 38;
    
    @Test
    public void testHandleRequest() throws IOException {
        FibonacciRequest fibRequest = new FibonacciRequest(38);
        
        String inputRequest = String.format("{\"body\" : %s}", GSON.toJson(fibRequest));
        System.out.println(inputRequest);
        
        InputStream inputStream = new ByteArrayInputStream(inputRequest.getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        
        Context mockContext = PowerMock.createMock(Context.class);
        EasyMock.expect(mockContext.getAwsRequestId()).andReturn(REQUEST_ID);
        EasyMock.expect(mockContext.getLogStreamName()).andReturn(RAW_HOST_ID);
        PowerMock.replay(mockContext);
        
        FibonacciFunction sut = new FibonacciFunction();
        sut.handleRequest(inputStream, outputStream, mockContext);
        
        FibonacciResponse fibResponse = GSON.fromJson(outputStream.toString(), FibonacciResponse.class);
        
        Assert.assertEquals(fibResponse.getNthNumber(), NTH_NUMBER);
        Assert.assertEquals(fibResponse.getFunctionName(), FUNCTION_NAME);
        Assert.assertEquals(fibResponse.getResult(), RESULT);
        
        Assert.assertEquals(fibResponse.getInvocationId(), REQUEST_ID);
        Assert.assertEquals(fibResponse.getHostId(), HOST_ID);
        PowerMock.verifyAll();
    }
}
