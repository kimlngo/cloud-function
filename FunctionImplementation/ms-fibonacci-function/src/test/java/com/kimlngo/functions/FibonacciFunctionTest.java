package com.kimlngo.functions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.google.gson.Gson;
import com.kimlngo.request.FibonacciRequest;
import com.kimlngo.response.FibonacciResponse;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;

public class FibonacciFunctionTest {
    private static final Gson GSON = new Gson();
    private static final int NTH_NUMBER = 38;
    private static final String INVOCATION_ID = "123";
    private static final int RESULT = 39088169;
    
    @Test
    public void testHttpTriggerJava() throws Exception {
        // Setup
        @SuppressWarnings("unchecked")
        final HttpRequestMessage<Optional<FibonacciRequest>> req = mock(HttpRequestMessage.class);

        FibonacciRequest fibRequest = new FibonacciRequest();
        fibRequest.setNthNumber(38);
        
        final Optional<FibonacciRequest> queryBody = Optional.of(fibRequest);
        doReturn(queryBody).when(req).getBody();

        doAnswer(new Answer<HttpResponseMessage.Builder>() {
            @Override
            public HttpResponseMessage.Builder answer(InvocationOnMock invocation) {
                HttpStatus status = (HttpStatus) invocation.getArguments()[0];
                return new HttpResponseMessageMock.HttpResponseMessageBuilderMock().status(status);
            }
        }).when(req).createResponseBuilder(any(HttpStatus.class));

        final ExecutionContext context = mock(ExecutionContext.class);
        doReturn(Logger.getGlobal()).when(context).getLogger();
        doReturn(INVOCATION_ID).when(context).getInvocationId();

        // Invoke
        final HttpResponseMessage ret = new FibonacciFunction().process(req, context);

        // Verify
        assertEquals(ret.getStatus(), HttpStatus.OK);
        
        FibonacciResponse fibResponse = GSON.fromJson(String.valueOf(ret.getBody()), FibonacciResponse.class);
        assertEquals(fibResponse.getNthNumber(), NTH_NUMBER);
        assertEquals(fibResponse.getInvocationId(), INVOCATION_ID);
        assertEquals(fibResponse.getResult(), RESULT);
    }
}
