# gov-project

## Prerequisites
 - Java 8 - ensure your JAVA_HOME env variable is setup
 - Gradle 5.6.1 - ensure your GRADLE_HOME env variable is setup
 - IDE for Java & Spring - STS recommended

## Getting Started - Developer
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
Use Git commands to clone the repository to your local machine.

 - git clone project
 - If you are using STS or Eclipse import the project as gradle project.
 - Refresh your Gradle project dependencies

## Deployment

 - Edit application.properties file if necessary
 - Use gradle task 'build' to test application.
 - Use gradle task 'bootJar' to create Spring application.
 - You can run the application from the build directory using command 'java -jar gov-project-0.0.1-SNAPSHOT.jar'.
