# base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="code@plankdev.com"

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Environment variable to the application's jar file (can be changed from via build parameter)
ARG JAR_FILE=target/datetime-api-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} datetime-api.jar

# Run the jar file (setting security urandom is a workaround to startup tomcat quicker, see: https://spring.io/guides/gs/spring-boot-docker/#_containerize_it)
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/datetime-api.jar"]