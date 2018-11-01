Spring DateTime Api
==============================

# Description
This application is a API to calculate days, weekdays and weeks between two dates. 
Timzones and two different output types are currently supported.

## Technologies
Spring Boot (Java), Docker

## Goals
* The goal of this project is to provide a simple API to calculate days, weekdays and weeks between two dates.
* This application should eventually be showcasing deployment in the cloud utilising a complete CI/CD Toolchain to test, build and deploy the application.
* Amazon Elastic Container Service for Kubernetes or Google Kubernetes Engine should be used.
* Eventually all best practices should be included in a simple but production ready state. 

## Current development state
This project is in development and not yet production ready.

# Table of Contents:
* Installation
* Usage
* Build and Deploy
* TODO's
* License

# Installation 
This guide describes how to run the application on a local linux machine. 

## Preconditions
* Docker Environment needs to be installed.
* Instructions are written for Ubuntu.
* execute `sudo -s` in command line to copy and paste the following commands, otherwise sudo is always needed.

## Pull and run Docker image from Dockerhub
* pull image from dockerhub: `docker pull jp052/datetime-api`
* run detached: `docker run --name datetime-api-container -d -p 8080:8080 datetime-api`
* run with console output: `docker run --name datetime-api-container -p 8080:8080 jp052/datetime-api`
* Dockerhub repo: https://hub.docker.com/r/jp052/datetime-api/

# Usage
This guide demonstrates how to use the datetime api, with all available enpoints and query parameters.

## API Endpoints
* When run locally use: http://localhost:8080
* Full example URL: `http://localhost:8080/api/v1/datetime/days?startDateTime=2018-09-05T05:30&endDateTime=2018-09-10T23:30&startZone=Australia/Adelaide&endZone=Europe/Berlin&format=y:H:m:s`

### Available Endpoints
* /api/v1/datetime/days
* /api/v1/datetime/weekdays
* /api/v1/datetime/weeks

### Mandatory Query Parameters:
* startDateTime and endDateTime 
  * Use ISO DateTime Format yyyy-MM-dd'T'HH:mm:ss.SSSZ, e.g. "2000-10-31T01:30:00.000-05:00"
  * Example: /api/v1/datetime/days?startDateTime=2018-09-05T05:30&endDateTime=2018-09-10T05:30

### Optional Query Parameters:
* startZone and endZone
  * any Java Timezone Id is supported, see: https://garygregory.wordpress.com/2013/06/18/what-are-the-java-timezone-ids/
  * Example: startZone=Australia/Adelaide
* format
  * Pass any value to format output in years:hours:minuts:seconds
  * Example: `format=y:H:m:s`

# Build and Deploy
This guide shows all steps necessary to build the app and publish image to Dockerhub on a local machine

* navigate console into project directory
* run maven clean install in cmd or IDE to run tests and build application: `./mvnw clean install`
* delte old container: `docker rm <contaierId>`
* delte old image: `docker rmi -f imageId>` 
* build new image: `docker build -t datetime-api .`
* test new image: `docker-compose up`
* tag image: `docker tag datetime-api jp052/datetime-api`
* login dockerhub: `docker login --username jp052 --password mypw` 
* push: `docker push jp052/datetime-api`

# TODO's
All open todo's are listed as an overview here. (should be replaced

- [ ] Write JavaDoc
- [ ] Add timezone test for weekdays and weeks endpoint
- [ ] Add custom exception and error handling
- [ ] Add checking with custom exceptions for wrong date and timezone formats
- [ ] Enhance README.md formatting
- [ ] Add code overview documentation explaining architecture and packages in github wiki
- [ ] Add failing tests
- [ ] Deploy application to a cloud service (Amazon or Google)
- [ ] Implement Jenkins Toolchain to build, test and deploy the application
- [ ] Add Rest documentation using Swagger or Spring REST Docs
- [ ] Add and configure FindBugs
- [ ] Add HATEOAS support when adding CRUDL controller with database 


# License
UNLICENSE. Please visit the UNLICENSE.txt for more information.