
# SwaggerGen #

Master 

[![Build Status](https://travis-ci.com/AES-Outreach/SwaggerGen.svg?token=Q2Kk8fYfCWzCrC5pSjcD&branch=master)](https://travis-ci.com/AES-Outreach/SwaggerGen)

**SwaggerGen** is API documentation at the code level.

Using a minimal set of Java decorators, we automagically generate Swagger *.yml* files and self-packaged *HTML* pages. We initially wanted to create this solution for ourselves because wiki pages, google docs, and javadocs weren't really up to the task. We required complete obfuscation of the backend code from our front-end developers, without forcing the backend staff to manage documentation separately from the code. We achieved this by using a simple set of class & method annotations that can represent all of the meaningful information that can describe an API.

# Installation #
This solution requires a **Maven Java project** to work. It is currently tightly bound to those tools in its current iteration.
Assuming that your Maven project is created and good to go, all you need to do is add the dependency and build option below to your *POM* file, if that isn't the case, you should learn about [maven](https://maven.apache.org/guides/getting-started/) before continuing.

We currently host the repository our our organization's Nexus repository. To add our plugin as dependency, you must first add the repo to your *pom.xml* file, then you also need to configurate the plugin execution's.

**Adding the our Nexus repository**
```
	<pluginRepositories>
		<pluginRepository>
			<id>repo.outreach.swaggergen.io-release</id>
			<name>Outreach Nexus Repo</name>
			<url>https://repo.outstem.io/repository/swaggergen/</url>
		</pluginRepository>
	</pluginRepositories>
```
**Adding the dependency**
```
<dependency>
	<groupId>com.github.aes-outreach</groupId>
	<artifactId>swaggen-annotations</artifactId>
	<version>0.0.1</version>
</dependency>
```
**Configuring the plugin's execution**
```
<build>
	...
	<plugins>
		...
		<plugin>
			<groupId>com.github.aes-outreach</groupId>
			<artifactId>swaggen-annotations</artifactId>
			<version>0.0.1</version>
			<configuration>
				<propertiesPath>config.properties</propertiesPath>
				<showLogs>true</showLogs>
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
		...
	</plugins>
	...
</build>
```

 # Generating documentation pages #
 Running a Maven build on a project importing a plugin will create a folder structure that matches your base paths. We provide a ruby script that uses the Redoc CLI NPM package to generate static HTML documentation out of your Swagger files. To run the ruby file it run  ```ruby generate_docs.ruby```. Alternatively, since we generate valid OpenAPI *.yml* files, you can use any of the SmartBear OpenAPI tools. 

# Example #
Given a sample servlet method meant to capture a PUT request:
```
@SwaggerGenClass(
    basePath="/localhost/test",
    title="Meeting",
    servers={
        "{scheme}://{environment}.localhost:{port} = This uses scheme, environment and port variables"
    },
    scheme="https",
    environment="api",
    port="8080",
    headers={"Content-Type=application/json"}
)
public class DemoEndpoint {


    /**
     * Fake endpoint for testing purposes
     * @param request fake request object
     * @param resp fake response object
     */
    @SwaggerGen(
        uri="/demo",
        title="DemoExample",
        method="POST",
        description="Post Servlet Description",
        headers={"language=en"},
        responses={
        		@SwaggerResponse(body=@SwaggerBody("/schemas/someJsonSchema.json")),
        		@SwaggerResponse(code=400),
        		@SwaggerResponse(code=401),
        		@SwaggerResponse(code=403),
        		@SwaggerResponse(code=500)        		
        }
    )
    public void doPost(Object request, Object resp) {
        RequestHandler.handle(request);
        ResponseHandler.handle(resp);
    }
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
