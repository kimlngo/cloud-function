package timer.task;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.TimerTask;

import client.provider.RequestCreator;
import response.extractor.ResponseExtractor;
import client.provider.ClientType;
import util.TimeUtil;
import writer.OutputFileWriter;

public class FunctionTimerTask extends TimerTask {
    private static final String OUTPUT_FORMAT = "%s\t%s\t%d\t%s";
    
    private RequestCreator requestCreator = RequestCreator.getInstance();
    private ResponseExtractor responseExtractor = ResponseExtractor.getInstance();
    private ClientType type;
    private OutputFileWriter outputWriter;
    private String requestBody;
    
    public FunctionTimerTask(ClientType type, int numOfIteration) {
        this.type = type;
        this.outputWriter = new OutputFileWriter();
        this.requestBody = String.format("{ \"nthNumber\" : %d}", numOfIteration);
    }

    @Override
    public void run() {
        try {
            long startTime = System.currentTimeMillis();
            
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse = client.send(requestCreator.createRequest(type, requestBody), BodyHandlers.ofString());
            
            long endTime = System.currentTimeMillis();
            
            String extractedResponse = responseExtractor.getExtractor(type).extractResponse(httpResponse);
            String output = String.format(OUTPUT_FORMAT, TimeUtil.getTime(), String.valueOf(type), (endTime - startTime), extractedResponse);
            System.out.println(output);
            outputWriter.writeData(type.getPath(), output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
