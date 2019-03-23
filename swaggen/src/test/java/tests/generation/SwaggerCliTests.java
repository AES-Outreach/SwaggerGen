package tests.generation;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import generator.SwaggerGenerator;

public class SwaggerCliTests {

	/**
	 * Unit test using swagger-cli system command to ensure that the generated
	 * swagger is valid A valid swagger will have an exit code of 0 when swagger-cli
	 * is run against it. (successful command)
	 * 
	 * @throws Exception
	 */
	@Test
	public void AllMethodsEndpointTest() {
		try {
			String filelocation = "generated/swagger/AllMethodsEndpointYaml.yaml";
			SwaggerGenerator.generateSwaggerFile(new Class<?>[] { MockAllMethodsEndpoint.class }, filelocation);
			Process process = Runtime.getRuntime().exec("cmd /c swagger-cli validate " + filelocation);
			readProcess(process);
			assertEquals(0, process.exitValue());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Unit test using swagger-cli system command to ensure that the generated
	 * swagger is valid A valid swagger will have an exit code of 0 when swagger-cli
	 * is run against it. (successful command)
	 * 
	 * @throws Exception
	 */
	@Test
	public void PostEndpointTest() {
		try {
			String filelocation = "generated/swagger/PostEndpointYaml.yaml";
			SwaggerGenerator.generateSwaggerFile(new Class<?>[] { MockPostEndpoint.class }, filelocation);
			Process process = Runtime.getRuntime().exec("cmd /c swagger-cli validate " + filelocation);
			readProcess(process);
			assertEquals(0, process.exitValue());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Unit test using swagger-cli system command to ensure that the generated
	 * swagger is valid A valid swagger will have an exit code of 0 when swagger-cli
	 * is run against it. (successful command)
	 * 
	 * @throws Exception
	 */
	@Test
	public void PutEndpointTest() {
		try {
			String filelocation = "generated/swagger/PutEndpointYaml.yaml";
			SwaggerGenerator.generateSwaggerFile(new Class<?>[] { MockPutEndpointWithSchema.class }, filelocation);
			Process process = Runtime.getRuntime().exec("cmd /c swagger-cli validate " + filelocation);
			readProcess(process);
			assertEquals(0, process.exitValue());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Unit test using swagger-cli system command to ensure that the generated
	 * swagger is valid A valid swagger will have an exit code of 0 when swagger-cli
	 * is run against it. (successful command)
	 * 
	 * @throws Exception
	 */
	@Test
	public void MinimalEndpointTest() {
		try {
			String filelocation = "generated/swagger/MinimalYaml.yaml";
			SwaggerGenerator.generateSwaggerFile(new Class<?>[] { MinimalGetEndpointTest.class }, filelocation);
			Process process = Runtime.getRuntime().exec("cmd /c swagger-cli validate " + filelocation);
			readProcess(process);
			assertEquals(0, process.exitValue());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readProcess(Process process) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String s;
		while ((s = reader.readLine()) != null) {
			System.out.println(s);
		}
	}

}
