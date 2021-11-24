# Preparation Steps
Before compilation, there are certain preparation steps to point the client program to your designated cloud function, please check and change accordingly by each cloud provider:

1) AWS Lambda
* Create an API HTTP URL for your Lambda cloud function by following the instructions in [Create HTTP API Gateway](Create-HTTP-API-Endpoint.pdf)
* Paste the URL to the field [AWS_FIBONACCI_URI](src/main/java/util/Constant.java#L7)

2) IBM Cloud Function
* Replace the <NAME_SPACE> in IBM cloud function URL with the name space you created your cloud function [IBM_FIBONACCI_URI](src/main/java/util/Constant.java#L8)
* Follow the instruction [Acquire-IBM-API-Key](Acquire-IBM-API-Key.pdf) to get the IBM API Key
* Once obtained the IBM API Key, you need to paste this key to [IBM_API_KEY](src/main/java/util/Constant.java#L18)

3) Azure Cloud Function
* Replace the [AZURE_FIBONACCI_URI](src/main/java/util/Constant.java#L9) with your function's URL.

# Compile and Run
Steps for compilation and run the client program
1) This client program should be compiled with Java version 9 and above. In the pom.xml, it is currently compiled with Java version 16, please modify the following lines for the right version:
```
<properties>
	<maven.compiler.source>16</maven.compiler.source>
	<maven.compiler.target>16</maven.compiler.target>
</properties>
```

2) To compile the client program, run command:
```
mvn clean package
```

3) To run the client application, run command:
```
mvn exec:java -Dexec.args="x y z"
```
x, y, z are in **minutes**
x is the invocation duration for AWS Lambda
y is the invocation duration for IBM Cloud Function
z is the invocation duration for Azure Cloud Function

sample command: 
```
mvn exec:java -Dexec.args="5 10 12"
```

This will configure the timers to invoke AWS Lambda every 5 minutes, IBM Cloud Fn every 10 minutes and Azure Cloud Fn every 12 minutes.

5) The results will be stored in the local file: 
AWS Lambda: [aws-idle-timeout](result/aws-idle-timeout.txt)
IBM Cloud Fn: [ibm-idle-timeout](result/ibm-idle-timeout.txt)
Azure Cloud Fn: [azure-idle-timeout](result/azure-idle-timeout.txt)
