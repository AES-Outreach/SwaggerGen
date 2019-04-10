package tests.annotation;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import domain.path.Endpoint;
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
		Map<String, Map<RequestMethod, Endpoint>> path = PathGenerator.generatePathsFromClassList(klasses);
		
		Endpoint endpoint = path.get("/base/endpoint").get(RequestMethod.POST);
		
		assertEquals(endpoint.getDescription(), "Serverlet Description");
		assertEquals(endpoint.getSummary(), "Serverlet Title");
		assertEquals(endpoint.getResponses().size(), 3);
		assertEquals(endpoint.getParameters().size(), 5);
		assertEquals(endpoint.getParameters().get(0).getSchema().getType(), ParamType.STRING);

	}
	
}
