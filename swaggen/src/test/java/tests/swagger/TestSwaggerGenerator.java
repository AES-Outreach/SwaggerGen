package tests.swagger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import generator.SwaggerGenerator;

public class TestSwaggerGenerator {

	/**
	 * Function that generates the Swagger file of an endpoint that is passed in. We
	 * then verify that the file is valid Swagger by leveraging the swagger-cli npm
	 * command by launch a sub-process and listening to the exit code.
	 * 
	 * @param filelocation location of the file to be generated, then tested.
	 * @param klass        class of the endpoint to be tested.
	 * @return the exit code of the swagger-cli process. 0 if success, any other
	 *         value is a failure.
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static void generate(String filelocation, Class<?> klass)
			throws JsonParseException, JsonMappingException, IOException {
		generateSwagger(filelocation, klass);
	}

	/**
	 * Function that calls the Swagger generation flow and eventually writes the
	 * swagger file for the endpoint passed in to the location passed in.
	 * 
	 * @param filelocation location of the swagger file to be generated
	 * @param klass class representing the test endpoint
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private static void generateSwagger(String filelocation, Class<?> klass)
			throws JsonParseException, JsonMappingException, IOException {
		SwaggerGenerator.generateSwaggerFile(new Class<?>[] { klass }, filelocation);
	}


}
