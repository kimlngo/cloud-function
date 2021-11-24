package client;

import java.util.Timer;
import java.util.TimerTask;

import client.provider.ClientType;
import timer.task.FunctionTimerTask;
import util.Constant;

public class Client {
    public static void main(String[] args) {
        int awsDuration = 0, ibmDuration = 0, azureDuration = 0;
        
        if(args.length == 0 || args.length > 3) {
            System.out.println("Please enter 3 arguments (e.g., -Dexec.args=\"x y z\") "
                    + "with x, y, z is the invocation duration for aws, ibm and azure, respectively."
                    + "If you don't want to test with a provider, enter value 0 for corresponding index");
            return;
        }
        
        awsDuration = Integer.parseInt(args[0]);
        ibmDuration = Integer.parseInt(args[1]);
        azureDuration = Integer.parseInt(args[2]);
    
        if(awsDuration != 0) {
            TimerTask awsIdleTimerTask = new FunctionTimerTask(ClientType.AWS_IDLE_TIMEOUT, Constant.FIBONACCI_TEST_WORKLOAD);
            new Timer().scheduleAtFixedRate(awsIdleTimerTask, Constant.ZERO_DELAY, awsDuration * Constant.ONE_MINUTE_INTERVAL);
        }
        
        if(ibmDuration != 0) {
            TimerTask ibmIdleTimerTask = new FunctionTimerTask(ClientType.IBM_IDLE_TIMEOUT, Constant.FIBONACCI_TEST_WORKLOAD);
            new Timer().scheduleAtFixedRate(ibmIdleTimerTask, Constant.ZERO_DELAY, ibmDuration * Constant.ONE_MINUTE_INTERVAL);
        }
        
        if(azureDuration != 0) {
            TimerTask azureIdleTimerTask = new FunctionTimerTask(ClientType.AZURE_IDLE_TIMEOUT, Constant.FIBONACCI_TEST_WORKLOAD);
            new Timer().scheduleAtFixedRate(azureIdleTimerTask, Constant.ZERO_DELAY, azureDuration * Constant.ONE_MINUTE_INTERVAL);
        }
    }
}
