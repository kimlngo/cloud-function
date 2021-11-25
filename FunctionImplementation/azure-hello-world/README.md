This repository contains the Hello World cloud function implementation for IBM Cloud Platform. The Hello World function receive a request and send back an echo message.
* Note that the maven project should be built with Java version 8 and above
* Build command line:
```
mvn clean package
```
* After successfully built, user can follow the steps in this document to run the cloud function locally or deploy to cloud environment [Quick start: Create a Java function in Azure from the command line](https://docs.microsoft.com/en-us/azure/azure-functions/create-first-function-cli-java?tabs=bash%2Cazure-cli%2Cbrowser)
* To login Azure from Command Line:
```
az login
```
* Once logged in, user can run the cloud function locally by:
```
mvn azure-functions:run
```
* To deploy the cloud function to cloud environment, use command:
```
mvn azure-functions:deploy
```