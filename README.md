# Jumia Solution
## Spring Boot Application
This is a sample Java / Maven / Spring Boot(version 2.6.3) For an API to Find out the pair of employees who have worked as a team the longest

## How to Run

This application is packaged as a war which has Tomcat embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository
* Make sure you are using JDK 17 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar -Dspring.profiles.active=test target/Mohamed-Mansour-employees-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=test"
```

## How to use

* Run the application
* Go to http://localhost:8086


