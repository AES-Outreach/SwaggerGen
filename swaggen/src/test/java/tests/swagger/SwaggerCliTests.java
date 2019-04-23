package tests.swagger;

import org.junit.Test;

import mock.endpoint.MinimalGetEndpointTest;
import mock.endpoint.MockAllMethodsEndpoint;
import mock.endpoint.MockPostEndpoint;
import mock.endpoint.MockPutEndpointWithSchema;

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
			TestSwaggerGenerator.generate("generated/swagger/AllMethodsEndpointYaml.yaml", MockAllMethodsEndpoint.class);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
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
			TestSwaggerGenerator.generate("generated/swagger/PostEndpointYaml.yaml", MockPostEndpoint.class);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
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
			TestSwaggerGenerator.generate("generated/swagger/PutEndpointYaml.yaml", MockPutEndpointWithSchema.class);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
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
			TestSwaggerGenerator.generate("generated/swagger/MinimalYaml.yaml", MinimalGetEndpointTest.class);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
	}
	
	

}
