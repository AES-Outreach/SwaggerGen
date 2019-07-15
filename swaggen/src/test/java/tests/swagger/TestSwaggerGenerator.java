package tests.swagger;

import java.io.IOException;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import generator.SwaggerGenerator;

public class TestSwaggerGenerator {

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
	public static void generateSwagger(Class<?> klass)
			throws JsonParseException, JsonMappingException, IOException {
		Properties prop = new Properties();
		prop.put("version", "3.0.0");
		SwaggerGenerator.generateSwaggerFile(new Class<?>[] { klass }, prop);
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
	public static void generateSwagger(Class<?>[] klasses) 
		throws JsonParseException, JsonMappingException, IOException {
		SwaggerGenerator.generateSwaggerFile(klasses);
	}

}
