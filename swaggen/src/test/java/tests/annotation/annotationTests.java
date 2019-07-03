package tests.annotation;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.List;

import org.junit.Test;

import domain.output.path.Endpoint;
import domain.output.SwaggerEndpoint;
import enums.ParamType;
import enums.RequestMethod;
import generator.PathGenerator;
import mock.endpoint.MockPostEndpoint;


/**
 * Tests the annotation
 * 
 * @author William Gardiner (7267012)
 */
public class annotationTests {

	/**
	 * Test the annotation and factories by generating a path.
	 */
	@Test
	public void testAnnotation() {
		
		Class<?>[] klasses = {MockPostEndpoint.class};
		Map<String, List<SwaggerEndpoint>> path = generator.PathGenerator.generatePathsFromClassList(klasses);
		
		Endpoint endpoint = null;

		for (SwaggerEndpoint Swagger: path.get("base/endpoint")) {
			if (Swagger.getEndpoint(RequestMethod.POST) != null) {
				endpoint = Swagger.getEndpoint(RequestMethod.POST);
			}
		}
		
		assertEquals(endpoint.getDescription(), "Serverlet Description");
		assertEquals(endpoint.getSummary(), "Serverlet Title");
		assertEquals(endpoint.getResponses().size(), 3);
		assertEquals(endpoint.getParameters().size(), 5);
		assertEquals(endpoint.getParameters().get(0).getSchema().getType(), ParamType.STRING);

	}
	
}
