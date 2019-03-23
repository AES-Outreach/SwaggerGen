package tests.swagger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import generator.SwaggerGenerator;

public class SwagValidator {

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
	public static int validate(String filelocation, Class<?> klass)
			throws JsonParseException, JsonMappingException, IOException {
		generateSwagger(filelocation, klass);
		Process process = runSwaggerCLIProcess(filelocation);
		return process.exitValue();
	}

	/**
	 * Function that handles the generation of the swagger-cli processes.
	 * 
	 * @param filelocation locations of the swagger file to be tested.
	 * @return the object reference to the subprocess
	 * @throws IOException
	 */
	private static Process runSwaggerCLIProcess(String filelocation) throws IOException {
		Process process = Runtime.getRuntime().exec("cmd /c swagger-cli validate " + filelocation);
		readOutput(process);
		return process;
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

	/**
	 * Utility function to read the stream of the process.
	 * @param process swagger-cli process
	 * @throws IOException
	 */
	private static void readOutput(Process process) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String s;
		while ((s = reader.readLine()) != null) {
			System.out.println(s);
		}
	}
}
