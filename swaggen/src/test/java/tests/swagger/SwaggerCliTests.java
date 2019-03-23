package tests.swagger;

import static org.junit.Assert.assertEquals;

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
			assertEquals(0, SwagValidator.validate("generated/swagger/AllMethodsEndpointYaml.yaml", MockAllMethodsEndpoint.class));
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
			assertEquals(0, SwagValidator.validate("generated/swagger/PostEndpointYaml.yaml", MockPostEndpoint.class));
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
			assertEquals(0, SwagValidator.validate("generated/swagger/PutEndpointYaml.yaml", MockPutEndpointWithSchema.class));
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
			assertEquals(0, SwagValidator.validate("generated/swagger/MinimalYaml.yaml", MinimalGetEndpointTest.class));
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
	}
	
	

}
