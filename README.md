# SwaggerGen #
Develop

![Travis - Develop branch](https://travis-ci.com/AES-Outreach/SwaggerGen.svg?token=cukQqq8P2pkD1EpgeNAZ&branch=develop)

Master 

[![Build Status](https://travis-ci.com/AES-Outreach/SwaggerGen.svg?token=Q2Kk8fYfCWzCrC5pSjcD&branch=master)](https://travis-ci.com/AES-Outreach/SwaggerGen)

**SwaggerGen** is API documentation at the code level.

Using a minimal set of Java decorators, we automagically generate Swagger *.yml* files and self-packaged *HTML* pages. We initially wanted to create this solution for ourselves because wiki pages, google docs, and javadocs weren't really up to the task. We required complete obfuscation of the backend code from our front-end developers, without forcing the backend staff to manage documentation separately from the code. We achieved this by using a simple set of class & method annotations that can represent all of the meaningful information that can describe an API.

# Installation #
This solution requires a **Maven Java project** to work. It is currently tightly bound to those tools in its current iteration.
Assuming that your Maven project is created and good to go, all you need to do is add the dependency and build option below to your *POM* file, if that isn't the case, you should learn about [maven](https://maven.apache.org/guides/getting-started/) before continuing.

Currently, the process of setting up the project will require you to clone the repo and run *mvn clean install* inside of the swaggen folder to add the dependency to your local *.m2* folder. We're waiting on a response to add our project to the public maven repositories.

```
<pluginRepositories>
	<pluginRepository>
		<id>repo.outreach.uottawa.io-release</id>
		<name>Plugin Outreach Nexus Repository</name>
		<url>http://34.204.223.49:8080/repository/maven-releases/</url>
	</pluginRepository>
</pluginRepositories>
```
```
<!-- SwaggerGen plugin -->
<dependency>
	<groupId>com.github.aes-outreach</groupId>
		<artifactId>swaggen-annotations</artifactId>
	<version>0.0.1</version>
</dependency>
```
```
<plugin>
	<groupId>com.github.aes-outreach</groupId>
	<artifactId>swaggen-annotations</artifactId>
	<configuration>
		<propertiesPath>config.properties</propertiesPath>
		<showLogs>false</showLogs>
	</configuration>
	<executions>
		<execution>
			<phase>install</phase>
			<goals>
				<goal>process-annotations</goal>
			</goals>
		</execution>
	</executions>
</plugin>
```

# Example #
Given a sample servlet method meant to capture a PUT request:
```
@SwaggerGen(
  url= "/localhost/test/put",
	method="PUT",
	title="Meeting",
	description="This is a sample PUT endpoint description to be "
		+ "printed in a Swagger format HTML documentation file.",
	headers={"Content-Type=application/json", 
		"authorization=token used for authorization", 
		"langHeader=expected language of the request"},
	body="schemas/all/meetingPut.json",
	responses={"200=OK", "400", "404", "401", "403"},
	scheme="HTTP")
public void doPut(Object request, Object resp) {
	RequestHandler.handle(request);
	ResponseHandler.handle(resp);
}
```
When the project containing the servlet is built, the following swagger file is generated:
```
---
openapi: "3.0.0"
info:
  title: "Title"
  description: "Description"
  version: "0.0"
paths:
  /localhost/test/put:
    put:
      summary: "Meeting"
      description: "This is a sample PUT endpoint description to be printed in a Swagger\
        \ format HTML documentation file."
      parameters:
      - name: "authorization"
        description: "token used for authorization"
        schema:
          type: "string"
        required: false
        in: "header"
      - name: "langHeader"
        description: "expected language of the request"
        schema:
          type: "string"
        required: false
        in: "header"
      - name: "Content-Type"
        description: "application/json"
        schema:
          type: "string"
        required: false
        in: "header"
      responses:
        200:
          description: "OK"
        400:
          description: "No description"
        401:
          description: "No description"
        403:
          description: "No description"
        404:
          description: "No description"
```
Which can be used to generate single page HTML files that look as such (we currently use redoc-cli for this):
![image](https://i.imgur.com/eBNjfRx.png)
