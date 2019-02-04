package tests.pathgen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import enums.RequestMethod;
import generator.PathGenerator;
import resource.Endpoint;
import resource.Path;

public class PathGeneratorSingleEndpointTests extends BasePathGeneratorTest {
	
	@Before
	public void beforeEach() {
		Class<?>[] klasses = new Class<?>[1];
		klasses[0] = endpoint.MockPostEndpoint.class;
		paths = PathGenerator.generatePathsFromClassList(klasses);
	}
	
	@Test
	public void testStructure() {
		boolean failed = false;
		
		if(paths.size() == 1) {
			Path path = paths.get(0);
			Map<RequestMethod, Endpoint> endpoints = path.getEndpoints();
			if(!endpoints.containsKey(RequestMethod.POST)) {
				failed = true;
			}
		} else {
			failed = true;
		}
		assertFalse(failed);
	}
	
	@Test
	public void testUrl() {
		assertEquals(paths.get(0).getPath(), "/base/endpoint");
	}
	
	@Test
	public void testDescription() {
		assertEquals(paths.get(0).getEndpoints().get(RequestMethod.POST).getDescription(), "Serverlet Description");
	}
	
	@Test
	public void testParametersQuantity() {
		assertEquals(paths.get(0).getEndpoints().get(RequestMethod.POST).getParameters().size(), 5);
	}

}
