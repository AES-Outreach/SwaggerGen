package tests.swagger;

import org.junit.Test;

import mock.endpoint.MockMinimalGetEndpoint;
import mock.endpoint.MockAllMethodsEndpoint;
import mock.endpoint.MockClassAnnotationEndpoint;
import mock.endpoint.MockGetPostEndpoint;
import mock.endpoint.MockMinimalPostEndpoint;
import mock.endpoint.MockPostEndpoint;
import mock.endpoint.MockMinimalPutEndpoint;
import mock.endpoint.MockPutEndpointWithSchema;
import mock.endpoint.MockShareBasePath;
import mock.endpoint.MultiplePathsEndpoint;
import mock.endpoint.MockNoBasePathEndpoint;
import mock.endpoint.MockPutDeleteEndpoint;
import mock.endpoint.MockNonSwaggerMethods;

public class EndToEndTests {


	/**
	 * Test to generate a Swagger file using the MockAllMethodsEndpoint mock endpoint.
	 */
	@Test
	public void AllMethodsEndpointTest() {
		try {
			TestSwaggerGenerator.generateSwagger(MockAllMethodsEndpoint.class);
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
			TestSwaggerGenerator.generateSwagger(MockPostEndpoint.class);
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
			TestSwaggerGenerator.generateSwagger(MockPutEndpointWithSchema.class);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
	}

	/**
	 * Test to generate a Swagger file using the MockMinimalGetEndpointTest mock endpoint.
	 */
	@Test
	public void MinimalEndpointTest() {
		try {
			TestSwaggerGenerator.generateSwagger(MockMinimalGetEndpoint.class);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
	}

	/**
	 * Test to generate Swagger file in same folder from different classes.
	 */
	@Test
	public void MultipleFileEndpointTest() {
		try {
			TestSwaggerGenerator.generateSwagger(new Class<?>[]{MockMinimalPutEndpoint.class, 
				MockMinimalPostEndpoint.class, MockMinimalGetEndpoint.class});
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
	}

	/**
	 * Test to generate Swagger file with more than one class
	 */
	@Test
	public void MultipleClassesEndpointTest() {
		try {
			TestSwaggerGenerator.generateSwagger(new Class<?>[]{MockMinimalPutEndpoint.class, 
				MockPutEndpointWithSchema.class, MockMinimalPostEndpoint.class, MockMinimalGetEndpoint.class,
				MockAllMethodsEndpoint.class, MockPostEndpoint.class});
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
	}
	/**
	 * Test to generate endpoints that share base paths
	 */
	@Test
	public void ShareBasePathsTest() {
		try {
			TestSwaggerGenerator.generateSwagger(new Class<?>[]{MockShareBasePath.class, MockPostEndpoint.class});
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
	}
	/**
	 * Test to generate endpoint that have no base path
	 */
	@Test
	public void NoBasePathsTest() {
		try {
			TestSwaggerGenerator.generateSwagger(MockNoBasePathEndpoint.class);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
	}

	/**
	 * Test to generate multiple request methods from different classes
	 */
	@Test
	public void combineDifferentClasses() {
		try {
			TestSwaggerGenerator.generateSwagger(new Class<?>[]{MockGetPostEndpoint.class, MockPutDeleteEndpoint.class});
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
	}

	/**
	 * Test to generate an endpoint that is already generated to see if there is 
	 * duplication
	 */
	@Test
	public void AlreadyGeneratedEndpointTest() {
		try {
			TestSwaggerGenerator.generateSwagger(MockPostEndpoint.class);
			TestSwaggerGenerator.generateSwagger(MockPostEndpoint.class);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
	}
	/**
	 * Test to generate different paths from the same class
	 */
	@Test
	public void MultiplePathsEndpointTest() {
		try {
			TestSwaggerGenerator.generateSwagger(new Class<?>[]{MultiplePathsEndpoint.class, MockPostEndpoint.class});
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
	}
	/**
	 * Test for using class level annotation 
	 */
	@Test
	public void ClassAnnotationsTest() {
		try {
			TestSwaggerGenerator.generateSwagger(new Class<?>[]{MockClassAnnotationEndpoint.class});
			} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
	}

	/**
	 * Test to generate yaml with a class that has both annotated and non annotated endpoints
	 */
	@Test
	public void NonSwaggerMethodsTest() {
		try {
			TestSwaggerGenerator.generateSwagger(MockNonSwaggerMethods.class);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
	}
}
