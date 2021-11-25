This repository contains the Hello World cloud function implementation for AWS Lambda. The Hello World function receive a request and send back an echo message.
* Note that the maven project should be built with Java version 8 and above
* The function memory used in the experiment is 512MB
* Build command line:
```
mvn clean package
```
* After the building, the **aws-hello-world-1.0.0-SNAPSHOT.jar** will be created and user can upload this jar to AWS Lambda Function in AWS Console