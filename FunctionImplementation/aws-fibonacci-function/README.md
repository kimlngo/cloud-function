This repository folder contains the function implementation of AWS Lambda Fibonacci nth number calculation. 
* The calculation logic is performed recursively with fib(n) = fib(n-1) + f(n-2)
* Note that the maven project should be built with Java version 8 and above
* The function memory used in the experiment is 512MB
* Build command line:
```
mvn clean package
```
* After the building, the **aws.fibonacci-1.0.0-SNAPSHOT.jar** will be created and user can upload this jar to AWS Lambda Function in AWS Console