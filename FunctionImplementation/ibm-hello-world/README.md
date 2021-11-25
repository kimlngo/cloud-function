This repository contains the Hello World cloud function implementation for IBM Cloud Platform. The Hello World function receive a request and send back an echo message.
* Note that the maven project should be built with Java version 8 and above
* The function memory used in the experiment is 512MB
* Build command line:
```
mvn clean package
```
* After the building, the **ibm-hello-world-1.0.0-SNAPSHOT.jar** will be created.
* User can deploy the jar package to IBM Cloud Function using the following commands:

1) If it is the first deployment, user needs to use "create action" command
```
ibmcloud fn action create hello-world-function target/ibm-hello-world-1.0.0-SNAPSHOT.jar --kind java:8 --memory 512 --timeout 15000 --main ibm.faas.HelloWorldFunction#process
```

2) For subsequent update, user can use the "Update action" command
```
ibmcloud fn action update hello-world-function target/ibm-hello-world-1.0.0-SNAPSHOT.jar --main ibm.faas.HelloWorldFunction#process
```

* Note: function memory can be configured by either using the [IBM Function Management Webpage](https://cloud.ibm.com/functions) or re-run the update command with a new memory option added

For example:
```
ibmcloud fn action update hello-world-function target/ibm-hello-world-1.0.0-SNAPSHOT.jar --memory 1024 --main ibm.faas.HelloWorldFunction#process
```