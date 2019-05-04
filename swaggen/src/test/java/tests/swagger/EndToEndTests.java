package tests.swagger;

import org.junit.Test;

import mock.endpoint.MinimalGetEndpointTest;
import mock.endpoint.MockAllMethodsEndpoint;
import mock.endpoint.MockPostEndpoint;
import mock.endpoint.MockPutEndpointWithSchema;

public class EndToEndTests {


	/**
	 * Test to generate a Swagger file using the MockAllMethodsEndpoint mock endpoint.
	 */
	@Test
	public void AllMethodsEndpointTest() {
		try {
			TestSwaggerGenerator.generateSwagger("generated/swagger/AllMethodsEndpointYaml.yaml", MockAllMethodsEndpoint.class);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
	}


	/**
	 * Test to generate a Swagger file using the MockPostEndpoint mock endpoint.
	 */
	@Test
	public void PostEndpointTest() {
		try {
			TestSwaggerGenerator.generateSwagger("generated/swagger/PostEndpointYaml.yaml", MockPostEndpoint.class);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
	}


	/**
	 * Test to generate a Swagger file using the MockPutEndpointWithSchema mock endpoint.
	 */
	@Test
	public void PutEndpointTest() {
		try {
			TestSwaggerGenerator.generateSwagger("generated/swagger/PutEndpointYaml.yaml", MockPutEndpointWithSchema.class);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
	}

	/**
	 * Test to generate a Swagger file using the MinimalGetEndpointTest mock endpoint.
	 */
	@Test
	public void MinimalEndpointTest() {
		try {
			TestSwaggerGenerator.generateSwagger("generated/swagger/MinimalYaml.yaml", MinimalGetEndpointTest.class);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
	}
	
	

}
