# Technical Test Hotelbeds

* Rest Full Application capable of detecting ip Hacker by processing failed logging attempts
and compare two timestamp in RFC 2822 format and return number of minutes between these

## Getting Started

Clone or Download repository https://github.com/jesus301/technical-test-hacker-HB.git

### Prerequisites

Open the project in IDE (preferred Intellij IDEA) and consider installing Maven Server or
Maven plugin server.
Execute the follow command to compile the project

```
mvn clean install
```

### Runner the project

Run Class HackerDetectorApplication.class in your IDE server or copy technical-test-hacker-[version].jar
in your Tomcat Server

## Running the tests

Example the execute test

````
maven test
````

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [ConfigurationMaven](https://maven.apache.org/settings.html) - Server Configuration

## Example test with Postman

_Test Service Ip Hacker Detector_
**URL Service** http://[serverIp]:8080/api/hacker/detector/process-line
````
Request
80.238.9.179,133612947,SIGNIN_SUCCESS,Will.Smith
Response
Not Detected Hacker --> null
Detected Hacker --> 80.238.9.179
```` 

_Test Service Time Comparator_
**URL Service** http://[serverIp]:8080/api/comparator/time-comparator
````
Request
{
	"time1": "Thu, 21 Dec 2000 16:00:00 +0200",
	"time2": "Thu, 21 Dec 2001 16:40:58 +0500"
}
Response
525460
```` 


## Author

* **Jesús Párraga** - *Initial work*
